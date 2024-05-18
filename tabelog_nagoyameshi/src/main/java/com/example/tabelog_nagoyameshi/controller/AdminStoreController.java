package com.example.tabelog_nagoyameshi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog_nagoyameshi.entity.Category;
import com.example.tabelog_nagoyameshi.entity.Store;
import com.example.tabelog_nagoyameshi.form.StoreEditForm;
import com.example.tabelog_nagoyameshi.form.StoreRegisterForm;
import com.example.tabelog_nagoyameshi.repository.CategoryRepository;
import com.example.tabelog_nagoyameshi.repository.StoreRepository;
import com.example.tabelog_nagoyameshi.service.StoreService;

@Controller
public class AdminStoreController {
	private final StoreRepository storeRepository;
	private final CategoryRepository categoryRepository;
	private final StoreService storeService;
	
	public AdminStoreController(StoreRepository storeRepository, CategoryRepository categoryRepository, StoreService storeService) {
		this.storeRepository = storeRepository;
		this.categoryRepository = categoryRepository;
		this.storeService = storeService;
	}
	
	//店舗管理ページ
	@GetMapping("/login/stores")
	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "storeKeyword", required = false) String storeKeyword, @RequestParam(name = "categoryKeyword", required = false) String categoryKeyword, @RequestParam(name = "price", required = false) Integer price, @RequestParam(name = "order", required = false) String order) {
		Page<Store> storePage;
		
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
		model.addAttribute("storeKeyword", storeKeyword);
		model.addAttribute("categoryKeyword", categoryKeyword);
		model.addAttribute("order", order);
		model.addAttribute("categoryList", categoryList);
		
		return "admin/store/index";
	}
	
	//店舗登録ページ
	@GetMapping("/login/stores/register")
	public String register(Model model) {
		List<Category> categoryList = categoryRepository.findAll();
		
		model.addAttribute("storeRegisterForm", new StoreRegisterForm());
		model.addAttribute("categoryList", categoryList);
		
		return "admin/store/register";
	}
	
	//店舗情報編集ページ
	@GetMapping("login/stores/{id}")
	public String update(@PathVariable(name = "id")Integer id, Model model) {
		Store store = storeRepository.getReferenceById(id);
		StoreEditForm storeEditForm = new StoreEditForm(id, store.getCategory().getCategoryName(), store.getStoreName(), store.getFurigana(), store.getAlphabet(), null, store.getDescription(), store.getOpeningHour(), store.getClosingHour(), store.getClosedDay(), store.getMinimumBudget(), store.getMaximumBudget(), store.getAddress(), store.getPhoneNumber(), store.getSeats());
		List<Category> categoryList = categoryRepository.findAll();
		
		model.addAttribute("store", store);
		model.addAttribute("storeEditForm", storeEditForm);
		model.addAttribute("categoryList", categoryList);
		
		return "admin/store/edit";
	}
	
	//店舗登録処理
	@PostMapping("/login/stores/register/create")
	public String create(StoreRegisterForm storeRegisterForm, RedirectAttributes redirectAttributes) {
		storeService.create(storeRegisterForm);
		
		redirectAttributes.addFlashAttribute("createSuccessMessage", "店舗登録が完了しました。");
		
		return "redirect:/login/stores";
	}
	
	//店舗削除処理
	@PostMapping("/login/stores/{id}/delete")
	public String delete(@PathVariable(name = "id")Integer id, RedirectAttributes redirectAttributes) {
		storeRepository.deleteById(id);
		
		redirectAttributes.addFlashAttribute("deleteSuccessMessage", "店舗情報を削除しました。");
		
		return "redirect:/login/stores";
	}
	
	//店舗情報編集機能
	@PostMapping("/login/stores/{id}/update")
	public String update(@ModelAttribute @Validated StoreEditForm storeEditForm, RedirectAttributes redirectAttributes) {
		storeService.update(storeEditForm);
		
		redirectAttributes.addFlashAttribute("updateSuccessMessage", "店舗情報を変更しました。");
		
		return "redirect:/login/stores";
	}

}