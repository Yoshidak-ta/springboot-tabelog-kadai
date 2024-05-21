package com.example.tabelog_nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog_nagoyameshi.entity.Category;
import com.example.tabelog_nagoyameshi.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
	public Page<Store> findByStoreNameLikeOrAlphabetLikeOrFuriganaLikeOrderByCreatedAtDesc(String storeNameKeyword, String alphabetKeyword, String furiganaKeyword, Pageable pageable);
	public Page<Store> findByStoreNameLikeOrAlphabetLikeOrFuriganaLikeOrderByMinimumBudgetAsc(String storeNameKeyword, String alphabetKeyword, String furiganaKeyword, Pageable pageable);
	public Page<Store> findByStoreNameLikeOrAlphabetLikeOrFuriganaLikeOrderByFuriganaAsc(String storeNameKeyword, String alphabetKeyword, String furiganaKeyword, Pageable pageable);
	
	public Page<Store> findByCategoryOrderByCreatedAtDesc(Category category, Pageable pageable);
	public Page<Store> findByCategoryOrderByMinimumBudgetAsc(Category category, Pageable pageable);
	public Page<Store> findByCategoryOrderByFuriganaAsc(Category category, Pageable pageable);
	
	public Page<Store> findByMaximumBudgetLessThanEqualOrderByCreatedAtDesc(Integer price, Pageable ageable);
	public Page<Store> findByMaximumBudgetLessThanEqualOrderByMinimumBudgetAsc(Integer price, Pageable ageable);
	public Page<Store> findByMaximumBudgetLessThanEqualOrderByFuriganaAsc(Integer price, Pageable ageable);
	
	public Page<Store> findAllByOrderByCreatedAtDesc(Pageable ageable);
	public Page<Store> findAllByOrderByMinimumBudgetAsc(Pageable ageable);
	public Page<Store> findAllByOrderByFuriganaAsc(Pageable ageable);

}