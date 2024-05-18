package com.example.tabelog_nagoyameshi.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tabelog_nagoyameshi.entity.Reservation;
import com.example.tabelog_nagoyameshi.entity.Store;
import com.example.tabelog_nagoyameshi.entity.User;
import com.example.tabelog_nagoyameshi.form.ReservationEditForm;
import com.example.tabelog_nagoyameshi.form.ReservationRegisterForm;
import com.example.tabelog_nagoyameshi.repository.ReservationRepository;
import com.example.tabelog_nagoyameshi.repository.StoreRepository;
import com.example.tabelog_nagoyameshi.repository.UserRepository;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final UserRepository userRepository;
	private final StoreRepository storeRepository;
	
	public ReservationService(ReservationRepository reservationRepository, StoreRepository storeRepository, UserRepository userRepository) {
		this.reservationRepository = reservationRepository;
		this.userRepository = userRepository;
		this.storeRepository = storeRepository;
	}
	
	//予約追加機能
	@Transactional
	public void create(ReservationRegisterForm reservationRegisterForm) {
		Reservation reservation = new Reservation();
		User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
		Store store = storeRepository.getReferenceById(reservationRegisterForm.getStoreId());
		LocalDate bookingDate = reservationRegisterForm.getBookingDate();
		LocalTime bookingTime = reservationRegisterForm.getBookingTime();
		
		reservation.setUser(user);
		reservation.setStore(store);
		reservation.setBookingDate(bookingDate);
		reservation.setBookingTime(bookingTime);
		reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());
		
		reservationRepository.save(reservation);
	}
	
	//予約人数が店の席数を上回っているかどうか判定する
	public boolean isWithinCapacity(Integer numberOfPeople, Integer seats) {
		return numberOfPeople <= seats;
	}
	
	//予約変更機能
	@Transactional
	public void update(ReservationEditForm reservationEditForm) {
		Reservation reservation = reservationRepository.getReferenceById(reservationEditForm.getId());
		
		reservation.setUser(reservationEditForm.getUser());
		reservation.setStore(reservationEditForm.getStore());
		reservation.setBookingDate(reservationEditForm.getBookingDate());
		reservation.setBookingTime(reservationEditForm.getBookingTime());
		reservation.setNumberOfPeople(reservationEditForm.getNumberOfPeople());
		
		reservationRepository.save(reservation);
	}

}