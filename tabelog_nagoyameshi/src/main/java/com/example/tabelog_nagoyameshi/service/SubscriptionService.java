package com.example.tabelog_nagoyameshi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.tabelog_nagoyameshi.entity.Role;
import com.example.tabelog_nagoyameshi.entity.User;
import com.example.tabelog_nagoyameshi.repository.CardRepository;
import com.example.tabelog_nagoyameshi.repository.RoleRepository;
import com.example.tabelog_nagoyameshi.repository.UserRepository;

@Service
public class SubscriptionService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private CardRepository cardRepository;
	
	public void upgradeUserToPaid(String userName) {
		User user = userRepository.findByUserName(userName);
		if(cardRepository.findByUser(user) != null) {
			Role role = roleRepository.getReferenceById(2);
			user.setRole(role);
			userRepository.save(user);
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), AuthorityUtils.createAuthorityList("ROLE_PRIME"));
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}else if(cardRepository.findByUser(user) == null) {
			Role role = roleRepository.getReferenceById(1);
			user.setRole(role);
			userRepository.save(user);
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), AuthorityUtils.createAuthorityList("ROLE_GENERAL"));
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
	}

}