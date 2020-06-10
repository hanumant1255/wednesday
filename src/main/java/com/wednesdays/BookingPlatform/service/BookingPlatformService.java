package com.wednesdays.BookingPlatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wednesdays.BookingPlatform.models.Booking;
import com.wednesdays.BookingPlatform.models.Car;
import com.wednesdays.BookingPlatform.repository.BookingRepository;
import com.wednesdays.BookingPlatform.repository.CarRepository;

@Service
public class BookingPlatformService {
	
	@Autowired
	BookingRepository bookingPlatformRepository;
	@Autowired
	CarRepository carRepository;
	
	public void requestBooking(Booking booking){		
		bookingPlatformRepository.save(booking);
	}

	public List<Booking>getBookings(int userId){
		return bookingPlatformRepository.findByUserIdAndStatus(userId,"COMPLETED");
	}

	public List<Car>getCabs(String currentLocation){
		return carRepository.findByCurrentLocationAndStatus("COMPLETED","AVAILABLE");	
	}
}
