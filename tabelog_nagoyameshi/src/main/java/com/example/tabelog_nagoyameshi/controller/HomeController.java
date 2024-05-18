package com.example.tabelog_nagoyameshi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tabelog_nagoyameshi.entity.Category;
import com.example.tabelog_nagoyameshi.entity.Review;
import com.example.tabelog_nagoyameshi.entity.Store;
import com.example.tabelog_nagoyameshi.entity.User;
import com.example.tabelog_nagoyameshi.form.FavoriteRegisterForm;
import com.example.tabelog_nagoyameshi.form.ReservationInputForm;
import com.example.tabelog_nagoyameshi.repository.CategoryRepository;
import com.example.tabelog_nagoyameshi.repository.ReviewRepository;
import com.example.tabelog_nagoyameshi.repository.StoreRepository;
import com.example.tabelog_nagoyameshi.repository.UserRepository;
import com.example.tabelog_nagoyameshi.security.UserDetailsImpl;
import com.example.tabelog_nagoyameshi.service.FavoriteService;
import com.example.tabelog_nagoyameshi.service.ReviewService;

@Controller
@RequestMapping("/login")
public class HomeController {
	private final StoreRepository storeRepository;
	private final CategoryRepository categoryRepository;
	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final FavoriteService favoriteService;
	private final ReviewService reviewService;
	
	public HomeController(StoreRepository storeRepository, CategoryRepository categoryRepository, ReviewRepository reviewRepository, UserRepository userRepository, FavoriteService favoriteService, ReviewService reviewService) {
		this.storeRepository = storeRepository;
		this.categoryRepository = categoryRepository;
		this.reviewRepository = reviewRepository;
		this.userRepository = userRepository;
		this.favoriteService = favoriteService;
		this.reviewService = reviewService;
	}
	
	//ログイン後トップページ（全員共通）
	@GetMapping
	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "storeKeyword", required = false) String storeKeyword, @RequestParam(name = "categoryKeyword", required = false) String categoryKeyword, @RequestParam(name = "price", required = false) Integer price, @RequestParam(name = "order", required = false) String order, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		Page<Store> storePage;
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		
		//空文字チェックいれるか？switch分にするか？
		if(storeKeyword != null && !storeKeyword.isEmpty()) {
			if(order != null && order.equals("minimumBudgetAsc")) {
				storePage = storeRepository.findByStoreNameLikeOrAlphabetLikeOrFuriganaLikeOrderByMinimumBudgetAsc("%" + storeKeyword + "%", "%" + storeKeyword + "%", "%" + storeKeyword + "%", pageable);
			}else if(order != null && order.equals("furiganaAsc")) {
				storePage = storeRepository.findByStoreNameLikeOrAlphabetLikeOrFuriganaLikeOrderByFuriganaAsc("%" + storeKeyword + "%", "%" + storeKeyword + "%", "%" + storeKeyword + "%", pageable);
			}else{
				storePage = storeRepository.findByStoreNameLikeOrAlphabetLikeOrFuriganaLikeOrderByCreatedAtDesc("%" + storeKeyword + "%", "%" + storeKeyword + "%", "%" + storeKeyword + "%", pageable);
			}
		}else if(price != null) {
			if(order != null && order.equals("createdAtDesc")) {
				storePage = storeRepository.findByMinimumBudgetLessThanEqualOrderByCreatedAtDesc(price, pageable);
			}else if(order != null && order.equals("minimumBudgetAsc")) {
				storePage = storeRepository.findByMinimumBudgetLessThanEqualOrderByMinimumBudgetAsc(price, pageable);
			}else{
				storePage = storeRepository.findByMinimumBudgetLessThanEqualOrderByFuriganaAsc(price, pageable);
			}
		}else if(categoryKeyword != null && !categoryKeyword.isEmpty()) {
			if(order != null && order.equals("createdAtDesc")) {
				storePage = storeRepository.findByCategoryOrderByCreatedAtDesc(categoryRepository.findByCategoryNameLike("%" + categoryKeyword + "%"), pageable);
			}else if(order != null && order.equals("minimumBudgetAsc")) {
				storePage = storeRepository.findByCategoryOrderByMinimumBudgetAsc(categoryRepository.findByCategoryNameLike("%" + categoryKeyword + "%"), pageable);
			}else{
				storePage = storeRepository.findByCategoryOrderByFuriganaAsc(categoryRepository.findByCategoryNameLike("%" + categoryKeyword + "%"), pageable);
			}
		}else {
			if(order != null && order.equals("furiganaAsc")) {
				storePage = storeRepository.findAllByOrderByFuriganaAsc(pageable);
			}else if(order != null && order.equals("minimumBudgetAsc")) {
				storePage = storeRepository.findAllByOrderByMinimumBudgetAsc(pageable);
			}else{
				storePage = storeRepository.findAllByOrderByCreatedAtDesc(pageable);
			}
		}
		
		List<Category> categoryList = categoryRepository.findAll();
		
		model.addAttribute("storePage", storePage);
		model.addAttribute("user", user);
		model.addAttribute("storeKeyword", storeKeyword);
		model.addAttribute("categoryKeyword", categoryKeyword);
		model.addAttribute("order", order);
		model.addAttribute("categoryList", categoryList);
		
		return "index";
	}
	
	//店舗詳細
	@GetMapping("/{id}")
	public String show(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name = "id")Integer id, FavoriteRegisterForm favoriteRegisterForm, @PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
		Store store = storeRepository.getReferenceById(id);
		Page<Review> newReview = reviewRepository.findByStoreOrderByCreatedAtDesc(store, pageable);
		
		if(userDetailsImpl != null) {
			User user = userDetailsImpl.getUser();
			
			favoriteRegisterForm.setUserId(user.getId());
			favoriteRegisterForm.setStoreId(store.getId());
			
			boolean isFavorited = favoriteService.isFavoritedUserAndFavoritedStore(user, store);
			boolean isCreatedReview = reviewService.isCreatedReviewUserAndCreatedReviewStore(user, store);
			
			model.addAttribute("user", user);
			model.addAttribute("favoriteRegisterForm", favoriteRegisterForm);
			model.addAttribute("success", isFavorited);
			model.addAttribute("created", isCreatedReview);
		}
		
		model.addAttribute("store", store);
		model.addAttribute("reservationInputForm", new ReservationInputForm());
		model.addAttribute("newReview", newReview);
		
		return "show";
	}

}