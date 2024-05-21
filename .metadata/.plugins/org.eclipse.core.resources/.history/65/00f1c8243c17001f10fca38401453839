package com.example.tabelog_nagoyameshi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tabelog_nagoyameshi.entity.Role;
import com.example.tabelog_nagoyameshi.entity.User;
import com.example.tabelog_nagoyameshi.form.PasswordResetForm;
import com.example.tabelog_nagoyameshi.form.UserEditForm;
import com.example.tabelog_nagoyameshi.form.UserRegisterForm;
import com.example.tabelog_nagoyameshi.repository.CardRepository;
import com.example.tabelog_nagoyameshi.repository.RoleRepository;
import com.example.tabelog_nagoyameshi.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final CardRepository cardRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, RoleRepository roleRepository, CardRepository cardRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.cardRepository = cardRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	//会員登録機能
	@Transactional
	public User create(UserRegisterForm userInputForm) {
		User user = new User();
		Role role = roleRepository.findByRoleName2(userInputForm.getRoleName2());
		
		user.setUserName(userInputForm.getUserName());
		user.setFurigana(userInputForm.getFurigana());
		user.setPhoneNumber(userInputForm.getPhoneNumber());
		user.setEmail(userInputForm.getEmail());
		user.setPassword(passwordEncoder.encode(userInputForm.getPassword()));
		user.setRole(role);
		user.setEnabled(false);
		
		return userRepository.save(user);
	}
	
	//会員情報編集機能
	@Transactional
	public void update(UserEditForm userEditForm) {
		User user = userRepository.getReferenceById(userEditForm.getId());
		
		user.setUserName(userEditForm.getUserName());
		user.setFurigana(userEditForm.getFurigana());
		user.setEmail(userEditForm.getEmail());
		user.setPhoneNumber(userEditForm.getPhoneNumber());
		user.setEnabled(true);
		
		userRepository.save(user);
	}
	
	//メールアドレスが登録済みかどうか確認する
	public boolean isEmailRegistered(String email) {
		User user = userRepository.findByEmail(email);
		return user != null;
	}
	
	//パスワードとパスワード（確認用）が一致しているか確認する
	public boolean isSamePassword(String password, String passwordConfirmation) {
		return password.equals(passwordConfirmation);
	}
	
	//メールアドレスが変更されたか確認する
	public boolean isEmailChanged(UserEditForm userEditForm) {
		User currentUser = userRepository.getReferenceById(userEditForm.getId());
		return !userEditForm.getEmail().equals(currentUser.getEmail());
	}
	
	//ユーザーを有効にする
	@Transactional
	public void enableUser(User user) {
		user.setEnabled(true);
		userRepository.save(user);
	}
	
	//パスワードリセット機能
	@Transactional
	public void passwordUpdate(PasswordResetForm passwordResetForm) {
		User user = userRepository.findByEmail(passwordResetForm.getEmail());
		
		user.setPassword(passwordEncoder.encode(passwordResetForm.getPassword()));
		user.setEnabled(true);
		
		userRepository.save(user);
	}
	
	//サブスクアップグレード機能
	@Transactional
	public void roleUpdate(String email) {
		User user = userRepository.findByEmail(email);
		Role role = roleRepository.getReferenceById(2);
		
		user.setRole(role);
		
		userRepository.save(user);
	}
	
	//サブスク解約機能
		/*@Transactional
		public void typeDowngrade(String subscriptionId) {
			Card card = cardRepository.findBySubscriptionId(subscriptionId);
			User user = card.getUser();
			Type type = typeRepository.getReferenceById(1);
			
			user.setType(type);
			
			userRepository.save(user);
		}*/

}