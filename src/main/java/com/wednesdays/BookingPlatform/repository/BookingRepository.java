package com.wednesdays.BookingPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wednesdays.BookingPlatform.entity.Booking;
import com.wednesdays.BookingPlatform.entity.Car;


public interface BookingRepository extends JpaRepository<Booking, Long>{

	List<Booking> findByUserIdAndStatus(int userId,String status);
}
