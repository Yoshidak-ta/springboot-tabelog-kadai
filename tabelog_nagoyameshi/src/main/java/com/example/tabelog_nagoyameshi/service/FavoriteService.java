package com.example.tabelog_nagoyameshi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tabelog_nagoyameshi.entity.Favorite;
import com.example.tabelog_nagoyameshi.entity.Store;
import com.example.tabelog_nagoyameshi.entity.User;
import com.example.tabelog_nagoyameshi.form.FavoriteRegisterForm;
import com.example.tabelog_nagoyameshi.repository.FavoriteRepository;
import com.example.tabelog_nagoyameshi.repository.StoreRepository;
import com.example.tabelog_nagoyameshi.repository.UserRepository;

@Service
public class FavoriteService {
	private final FavoriteRepository favoriteRepository;
	private final UserRepository userRepository;
	private final StoreRepository storeRepository;
	
	public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, StoreRepository storeRepository) {
		this.favoriteRepository = favoriteRepository;
		this.userRepository = userRepository;
		this.storeRepository = storeRepository;
	}
	
	//お気に入り追加機能
	@Transactional
	public void create(FavoriteRegisterForm favoriteRegisterForm, User user, Store store) {
		Favorite favorite = new Favorite();
		
		favoriteRegisterForm.setUserId(user.getId());
		favoriteRegisterForm.setStoreId(store.getId());
		
		favorite.setUser(userRepository.getReferenceById(favoriteRegisterForm.getUserId()));
		favorite.setStore(storeRepository.getReferenceById(favoriteRegisterForm.getStoreId()));
		
		favoriteRepository.save(favorite);
	}
	
	//ユーザーがすでにお気に入り登録済みかどうか判定する
	@Transactional
	public boolean isFavoritedUserAndFavoritedStore(User user, Store store) {
		Favorite isFavorited = favoriteRepository.getByStoreIdAndUserId(store.getId(), user.getId());
		
		if(isFavorited != null) {
			return true;
		}
		
		return false;
	}

}