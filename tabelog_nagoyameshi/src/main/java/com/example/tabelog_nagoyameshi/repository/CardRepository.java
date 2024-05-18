package com.example.tabelog_nagoyameshi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.tabelog_nagoyameshi.entity.Card;
import com.example.tabelog_nagoyameshi.entity.User;


public interface CardRepository extends JpaRepository<Card, Integer> {
	public Card findByUser(User user);
	public Card findBySubscriptionId(String subscriptionId);
	
	@Transactional
	public Integer deleteBySubscriptionId(String subscriptionId);

}
