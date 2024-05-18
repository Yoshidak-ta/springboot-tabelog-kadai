package com.example.tabelog_nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog_nagoyameshi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	public Category findByCategoryNameLike(String keyword);
	public Category findByCategoryName(String categoryName);
	
	public Page<Category> findByCategoryNameLikeOrderByCategoryNameAsc(String keyword, Pageable pageable);
	public Page<Category> findAllByOrderByCategoryNameAsc(Pageable pageable);

}