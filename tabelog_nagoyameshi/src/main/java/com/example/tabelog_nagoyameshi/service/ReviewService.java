package com.example.tabelog_nagoyameshi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
//		MultipartFile photoName = reviewRegisterForm.getPhotoName();
//		
//		if(!photoName.isEmpty()) {
//			String imageName = photoName.getOriginalFilename();
//			String hashedImageName = generateNewFileName(imageName);
//			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
//			copyImageFile(photoName, filePath);
//			review.setPhotoName(hashedImageName);
//		}
		
		review.setUser(user);
		review.setStore(store);
		review.setStars(reviewRegisterForm.getStars());
		review.setReviewComment(reviewRegisterForm.getReviewComment());
		
		return reviewRepository.save(review);
	}
	
	public String generateNewFileName(String fileName) {
		 String[] fileNames = fileName.split("\\.");                
		 for (int i = 0; i < fileNames.length - 1; i++) {
			 fileNames[i] = UUID.randomUUID().toString();            
		 }
		 String hashedFileName = String.join(".", fileNames);
		 return hashedFileName;
	 }
	 
	 public void copyImageFile(MultipartFile imageFile, Path filePath) {           
		 try {
			 Files.copy(imageFile.getInputStream(), filePath);
		 } catch (IOException e) {
			 e.printStackTrace();
		 }          
	 }
	
	//レビュー変更機能
	@Transactional
	public void update(ReviewEditForm reviewEditForm) {
		Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
//		MultipartFile photoName = reviewEditForm.getPhotoName();
//		
//		if(!photoName.isEmpty()) {
//			String imageName = photoName.getOriginalFilename();
//			String hashedImageName = generateNewFileName(imageName);
//			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
//			copyImageFile(photoName, filePath);
//			review.setPhotoName(hashedImageName);
//		}
		
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