package com.example.tabelog_nagoyameshi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.tabelog_nagoyameshi.entity.Category;
import com.example.tabelog_nagoyameshi.entity.Store;
import com.example.tabelog_nagoyameshi.form.StoreEditForm;
import com.example.tabelog_nagoyameshi.form.StoreRegisterForm;
import com.example.tabelog_nagoyameshi.repository.CategoryRepository;
import com.example.tabelog_nagoyameshi.repository.StoreRepository;

@Service
public class StoreService {
	private final StoreRepository storeRepository;
	private final CategoryRepository categoryRepository;
	
	public StoreService(StoreRepository storeRepository, CategoryRepository categoryRepository) {
		this.storeRepository = storeRepository;
		this.categoryRepository = categoryRepository;
	}
	
	//店舗登録機能
	@Transactional
	public Store create(StoreRegisterForm storeRegisterForm) {
		Store store = new Store();
		Category category = categoryRepository.findByCategoryName(storeRegisterForm.getCategoryName());
		MultipartFile photoName = storeRegisterForm.getPhotoName();
		
		if(!photoName.isEmpty()) {
			String imageName = photoName.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/images/stores/" + hashedImageName);
			copyImageFile(photoName, filePath);
			store.setPhotoName(hashedImageName);
		}
		
		store.setCategory(category);
		store.setStoreName(storeRegisterForm.getStoreName());
		store.setFurigana(storeRegisterForm.getFurigana());
		store.setAlphabet(storeRegisterForm.getAlphabet());
		store.setDescription(storeRegisterForm.getDescription());
		store.setOpeningHour(storeRegisterForm.getOpeningHour());
		store.setClosingHour(storeRegisterForm.getClosingHour());
		store.setClosedDay(storeRegisterForm.getClosedDay());
		store.setMinimumBudget(storeRegisterForm.getMinimumBudget());
		store.setMaximumBudget(storeRegisterForm.getMaximumBudget());
		store.setAddress(storeRegisterForm.getAddress());
		store.setPhoneNumber(storeRegisterForm.getPhoneNumber());
		store.setSeats(storeRegisterForm.getSeats());
		
		return storeRepository.save(store);
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
	
	//店舗情報編集機能
	@Transactional
	public Store update(StoreEditForm storeEditForm) {
		Store store = storeRepository.getReferenceById(storeEditForm.getId());
		Category category = categoryRepository.findByCategoryName(storeEditForm.getCategoryName());
		MultipartFile photoName = storeEditForm.getPhotoName();
		
		if(!photoName.isEmpty()) {
			String imageName = photoName.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/images/stores/" + hashedImageName);
			copyImageFile(photoName, filePath);
			store.setPhotoName(hashedImageName);
		}
		
		store.setCategory(category);
		store.setStoreName(storeEditForm.getStoreName());
		store.setFurigana(storeEditForm.getFurigana());
		store.setAlphabet(storeEditForm.getAlphabet());
		store.setDescription(storeEditForm.getDescription());
		store.setOpeningHour(storeEditForm.getOpeningHour());
		store.setClosingHour(storeEditForm.getClosingHour());
		store.setClosedDay(storeEditForm.getClosedDay());
		store.setMinimumBudget(storeEditForm.getMinimumBudget());
		store.setMaximumBudget(storeEditForm.getMaximumBudget());
		store.setAddress(storeEditForm.getAddress());
		store.setPhoneNumber(storeEditForm.getPhoneNumber());
		store.setSeats(storeEditForm.getSeats());
		
		return storeRepository.save(store);
	}

}