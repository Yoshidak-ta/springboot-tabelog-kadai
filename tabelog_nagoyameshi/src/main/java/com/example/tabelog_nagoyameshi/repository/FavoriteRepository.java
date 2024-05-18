package com.example.tabelog_nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.tabelog_nagoyameshi.entity.Favorite;
import com.example.tabelog_nagoyameshi.entity.Store;
import com.example.tabelog_nagoyameshi.entity.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
	public List<Favorite> findByStore(Store store);
	public Page<Favorite> findByUser(User user, Pageable pageable);
	
	@Transactional
	public void deleteByStoreIdAndUserId(Integer storeId, Integer userId);
	
	public Favorite getByStoreIdAndUserId(Integer storeId, Integer userId);

}
