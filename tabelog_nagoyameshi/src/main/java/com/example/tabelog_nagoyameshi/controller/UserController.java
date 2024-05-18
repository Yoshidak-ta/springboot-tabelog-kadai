package com.example.tabelog_nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog_nagoyameshi.entity.Review;
import com.example.tabelog_nagoyameshi.entity.User;
import com.example.tabelog_nagoyameshi.form.UserEditForm;
import com.example.tabelog_nagoyameshi.repository.ReviewRepository;
import com.example.tabelog_nagoyameshi.security.UserDetailsImpl;
import com.example.tabelog_nagoyameshi.service.StripeService;
import com.example.tabelog_nagoyameshi.service.SubscriptionService;
import com.example.tabelog_nagoyameshi.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login/mypage")
public class UserController {
	private final ReviewRepository reviewRepository;
	private final UserService userService;
	private final StripeService stripeService;
	private final SubscriptionService subscriptionService;
	
	public UserController(ReviewRepository reviewRepository, UserService userService, StripeService stripeService, SubscriptionService subscriptionService) {
		this.reviewRepository = reviewRepository;
		this.userService = userService;
		this.stripeService = stripeService;
		this.subscriptionService = subscriptionService;
	}
	
	//マイページ
	@GetMapping
	public String mypage(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable, Model model, HttpServletRequest httpServletRequest) {
		User user = userDetailsImpl.getUser();
		Page<Review> newStores = reviewRepository.findByOrderByStarsAsc(pageable);
		subscriptionService.upgradeUserToPaid(user.getUserName());
		
		String sessionId = stripeService.updateStripeSession(httpServletRequest);
		
		model.addAttribute("user", user);
		model.addAttribute("newStores", newStores);
		model.addAttribute("sessionId", sessionId);
		
		return "mypage";
	}
	
	//会員情報編集ページ
	@GetMapping("/edit")
	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userDetailsImpl.getUser();
		
		UserEditForm userEditForm = new UserEditForm(user.getId(), user.getUserName(), user.getFurigana(), user.getPhoneNumber(), user.getEmail());
		
		model.addAttribute("userEditForm", userEditForm);
		
		return "edit";
	}
	
	@GetMapping("/subscription")
	public String subscription(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable, Model model, HttpServletRequest httpServletRequest) {
		User user = userDetailsImpl.getUser();
		
		String portalSessionUrl = stripeService.portalStripeSession(user, httpServletRequest);
		
		model.addAttribute("portalSessionUrl", portalSessionUrl);
		
		return "prime/subscription";
	}
	
	//会員情報変更処理
	@PostMapping("/update")
	public String update(@ModelAttribute @Validated UserEditForm userEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getEmail())) {
			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "登録済みのメールアドレスです。");
			bindingResult.addError(fieldError);
		}
		
		if(bindingResult.hasErrors()) {
			return "/edit";
		}
		
		userService.update(userEditForm);
		redirectAttributes.addFlashAttribute("updateSuccessMessage", "会員情報を編集しました。");
		
		return "redirect:/login/mypage";
	}

}