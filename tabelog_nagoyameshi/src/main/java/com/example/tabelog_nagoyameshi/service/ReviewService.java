package com.example.tabelog_nagoyameshi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tabelog_nagoyameshi.entity.Review;
import com.example.tabelog_nagoyameshi.entity.Store;
import com.example.tabelog_nagoyameshi.entity.User;
import com.example.tabelog_nagoyameshi.form.ReviewEditForm;
import com.example.tabelog_nagoyameshi.form.ReviewRegisterForm;
import com.example.tabelog_nagoyameshi.repository.ReviewRepository;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	//レビュー投稿機能
	@Transactional
	public Review create(User user, Store store, ReviewRegisterForm reviewRegisterForm) {
		Review review = new Review();
		
		review.setUser(user);
		review.setStore(store);
		review.setStars(reviewRegisterForm.getStars());
		review.setReviewComment(reviewRegisterForm.getReviewComment());
		
		return reviewRepository.save(review);
	}
	
	//レビュー変更機能
	@Transactional
	public void update(ReviewEditForm reviewEditForm) {
		Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
		
		review.setUser(reviewEditForm.getUser());
		review.setStore(reviewEditForm.getStore());
		review.setStars(reviewEditForm.getStars());
		review.setReviewComment(reviewEditForm.getReviewComment());
		
		reviewRepository.save(review);
	}
	
	//ユーザーがすでにレビュー投稿済みかどうか判定する
	@Transactional
	public boolean isCreatedReviewUserAndCreatedReviewStore(User user, Store store) {
		Review isCreated = reviewRepository.getByUserIdAndStoreId(user.getId(), store.getId());
		
		if(isCreated != null) {
			return false;
		}
		
		return true;
	}

}