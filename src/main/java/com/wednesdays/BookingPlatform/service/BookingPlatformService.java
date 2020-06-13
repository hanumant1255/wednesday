package com.wednesdays.BookingPlatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wednesdays.BookingPlatform.entity.Booking;
import com.wednesdays.BookingPlatform.entity.Car;
import com.wednesdays.BookingPlatform.model.BookingStatus;
import com.wednesdays.BookingPlatform.model.CarStatus;
import com.wednesdays.BookingPlatform.repository.BookingRepository;
import com.wednesdays.BookingPlatform.repository.CarRepository;

@Service
public class BookingPlatformService {

	@Autowired
	BookingRepository bookingPlatformRepository;

	@Autowired
	CarRepository carRepository;

	public Booking requestBooking(int userId, String src, String dest) {
		Booking bookingResp=new Booking();
		//Find available cars
		List<Car> availableCars=carRepository.findByCurrentLocationAndStatus(src, CarStatus.AVAILABLE.getStatus());
		if(availableCars!=null && !availableCars.isEmpty()) {
		Booking booking = new Booking();
		booking.setSrc(src);
		booking.setDest(dest);
		booking.setUserId(userId);
		booking.setCarId(availableCars.get(0).getCarId());
		booking.setStatus(BookingStatus.ALLOCATED.getStatus());
		bookingResp=bookingPlatformRepository.save(booking);
		Car car=new Car();
		car.setCarId(availableCars.get(0).getCarId());
		car.setStatus(CarStatus.NOT_AVAILABLE.getStatus());
		
		//Mark car status as not available
		carRepository.save(car);
		}
		return bookingResp;
	}

	public List<Booking> getBookings(int userId) {
		return bookingPlatformRepository.findByUserIdAndStatus(userId, BookingStatus.COMPLETED.getStatus());
	}

	public List<Car> getCabs(String currentLocation) {
		return carRepository.findByCurrentLocationAndStatus(currentLocation, CarStatus.AVAILABLE.getStatus());
	}
}
