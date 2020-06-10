package com.wednesdays.BookingPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wednesdays.BookingPlatform.models.Booking;
import com.wednesdays.BookingPlatform.models.Car;


public interface BookingRepository extends JpaRepository<Booking, Long>{

	List<Booking> findByUserIdAndStatus(int userId,String status);


}
