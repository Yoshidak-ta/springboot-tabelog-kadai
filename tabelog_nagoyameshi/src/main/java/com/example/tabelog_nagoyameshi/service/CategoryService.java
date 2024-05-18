package com.example.tabelog_nagoyameshi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tabelog_nagoyameshi.entity.Category;
import com.example.tabelog_nagoyameshi.form.CategoryEditForm;
import com.example.tabelog_nagoyameshi.form.CategoryRegisterForm;
import com.example.tabelog_nagoyameshi.repository.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	//カテゴリ登録機能
	@Transactional
	public Category create(CategoryRegisterForm categoryRegisterForm) {
		Category category = new Category();
		
		category.setCategoryName(categoryRegisterForm.getCategoryName());
		
		return categoryRepository.save(category);
	}
	
	//カテゴリ編集機能
	@Transactional
	public Category update(CategoryEditForm categoryEditForm) {
		Category category = categoryRepository.getReferenceById(categoryEditForm.getId());
		
		category.setCategoryName(categoryEditForm.getCategoryName());
		
		return categoryRepository.save(category);
	}

}