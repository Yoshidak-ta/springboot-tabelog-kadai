package com.example.tabelog_nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog_nagoyameshi.entity.Review;
import com.example.tabelog_nagoyameshi.entity.Store;
import com.example.tabelog_nagoyameshi.entity.User;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	public Page<Review> findByStoreOrderByCreatedAtDesc(Store store, Pageable pageable);
	public Page<Review> findByOrderByStarsAsc(Pageable pageable);
	public Page<Review> findByUserOrderByUpdatedAtDesc(User user, Pageable pageable);
	
	public Review getByUserIdAndStoreId(Integer userId, Integer storeId);

}