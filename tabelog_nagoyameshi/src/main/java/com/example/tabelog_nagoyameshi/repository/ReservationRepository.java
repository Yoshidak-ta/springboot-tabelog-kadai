package com.example.tabelog_nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog_nagoyameshi.entity.Reservation;
import com.example.tabelog_nagoyameshi.entity.User;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	public Page<Reservation> findByUserOrderByBookingDateAscBookingTimeAsc(User user, Pageable pageable);
	
	public List<Reservation> findByUser(User user);

}