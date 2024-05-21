package com.example.tabelog_nagoyameshi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog_nagoyameshi.entity.Review;
import com.example.tabelog_nagoyameshi.entity.Store;
import com.example.tabelog_nagoyameshi.entity.User;
import com.example.tabelog_nagoyameshi.form.ReviewEditForm;
import com.example.tabelog_nagoyameshi.form.ReviewRegisterForm;
import com.example.tabelog_nagoyameshi.repository.ReviewRepository;
import com.example.tabelog_nagoyameshi.repository.StoreRepository;
import com.example.tabelog_nagoyameshi.security.UserDetailsImpl;
import com.example.tabelog_nagoyameshi.service.ReviewService;

@Controller
@RequestMapping("/login")
public class ReviewController {
	private final ReviewService reviewService;
	private final ReviewRepository reviewRepository;
	private final StoreRepository storeRepository;
	
	public ReviewController(ReviewService reviewService, StoreRepository storeRepository, ReviewRepository reviewRepository) {
		this.reviewService = reviewService;
		this.storeRepository = storeRepository;
		this.reviewRepository = reviewRepository;
	}
	
	//レビュー一覧ページ
	@GetMapping("/reviews")
	public String reviews(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC)Pageable pageable, Model model) {
		User user = userDetailsImpl.getUser();
		
		List<Review> reviewList = reviewRepository.findByUser(user);
		Page<Review> newReview = reviewRepository.findByUserOrderByUpdatedAtDesc(user, pageable);
		
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("newReview", newReview);
		
		return "prime/reviews/index";
	}
	
	//レビュー投稿ページ
	@GetMapping("/{id}/review")
	public String review(@PathVariable(name = "id") Integer id, Model model) {
		Store store = storeRepository.getReferenceById(id);
		
		model.addAttribute("store", store);
		model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());
		
		return "prime/reviews/register";
	}
	
	//レビュー編集ページ
	@GetMapping("/reviews/{id}")
	public String reviewShow(@PathVariable(name = "id")Integer id, Model model) {
		Review review = reviewRepository.getReferenceById(id);
		ReviewEditForm reviewEditForm = new ReviewEditForm(id, review.getUser(), review.getStore(), review.getStars(), review.getReviewComment());
		
		model.addAttribute("review", review);
		model.addAttribute("reviewEditForm", reviewEditForm);
		
		return "prime/reviews/edit";
	}
	
	//レビュー投稿処理
	@PostMapping("/{id}/review/create")
	public String review(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name = "id") Integer id, ReviewRegisterForm reviewRegisterForm, RedirectAttributes redirectAttributes) {
		User user = userDetailsImpl.getUser();
		Store store = storeRepository.getReferenceById(id);
		
		reviewService.create(user, store, reviewRegisterForm);
		
		redirectAttributes.addFlashAttribute("createSuccessMessage", "レビューを投稿しました。");
		
		return "redirect:/login/reviews";
	}
	
	//レビュー変更処理
	@PostMapping("/reviews/{id}/update")
	public String update(@ModelAttribute @Validated ReviewEditForm reviewEditForm, RedirectAttributes redirectAttributes) {
		reviewService.update(reviewEditForm);
		
		redirectAttributes.addFlashAttribute("updateSuccessMessage", "レビューを更新しました。");
		
		return "redirect:/login/reviews";
	}
	
	//レビュー削除処理
	@PostMapping("/reviews/{id}/delete")
	public String delete(@PathVariable(name = "id")Integer id, RedirectAttributes redirectAttributes) {
		reviewRepository.deleteById(id);
		
		redirectAttributes.addFlashAttribute("deleteSuccessMessage", "レビューを削除しました。");
		
		return "redirect:/login/reviews";
	}

}
