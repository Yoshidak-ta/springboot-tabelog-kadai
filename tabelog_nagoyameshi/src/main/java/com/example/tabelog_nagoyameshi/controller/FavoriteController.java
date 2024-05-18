package com.example.tabelog_nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tabelog_nagoyameshi.entity.Favorite;
import com.example.tabelog_nagoyameshi.entity.Store;
import com.example.tabelog_nagoyameshi.entity.User;
import com.example.tabelog_nagoyameshi.form.FavoriteRegisterForm;
import com.example.tabelog_nagoyameshi.repository.FavoriteRepository;
import com.example.tabelog_nagoyameshi.repository.StoreRepository;
import com.example.tabelog_nagoyameshi.security.UserDetailsImpl;
import com.example.tabelog_nagoyameshi.service.FavoriteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class FavoriteController {
	private final FavoriteRepository favoriteRepository;
	private final FavoriteService favoriteService;
	private final StoreRepository storeRepository;
	
	public FavoriteController(FavoriteRepository favoriteRepository, FavoriteService favoriteService, StoreRepository storeRepository) {
		this.favoriteRepository = favoriteRepository;
		this.favoriteService = favoriteService;
		this.storeRepository = storeRepository;
	}
	
	//お気に入り一覧ページ
	@GetMapping("/favorites")
	public String favorites(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC)Pageable pageable, Model model, HttpSession httpSession) {
		User user = userDetailsImpl.getUser();
		Page<Favorite> favoritesPage = favoriteRepository.findByUser(user, pageable);
		httpSession.setAttribute("favoriteStore", storeRepository.findAll());
		
		model.addAttribute("user", user);
		model.addAttribute("favoritesPage", favoritesPage);
		
		return "prime/favorites/index";
	}
	
	//お気に入り登録処理
	@PostMapping("/{id}/create")
	public String create(@ModelAttribute FavoriteRegisterForm favoriteRegisterForm, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name = "id")Integer id, Model model) {
		Store store = storeRepository.getReferenceById(id);
		User user = userDetailsImpl.getUser();
		
		favoriteService.create(favoriteRegisterForm, user, store);
		
		model.addAttribute("favoriteRegisterForm", favoriteRegisterForm);
		
		return "redirect:/login/{id}";
	}
	
	//お気に入り解除処理
	@PostMapping("/{id}/delete")
	public String delete(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name = "id")Integer id, FavoriteRegisterForm favoriteRegisterForm, Model model) {
		Integer userId = userDetailsImpl.getUser().getId();
		favoriteRepository.deleteByStoreIdAndUserId(id, userId);
		favoriteRegisterForm.deleteByUserIdAndStoreId(userId, id);
		
		model.addAttribute("favoriteRegisterForm", favoriteRegisterForm);
		
		return "redirect:/login/{id}";
	}

}