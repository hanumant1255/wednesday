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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingPlatformService {

	
	private final BookingRepository bookingRepository;
	private final CarRepository carRepository;

	public Booking requestBooking(Booking booking) {
		Booking bookingResp=new Booking();
		//Find available cars
		List<Car> availableCars=carRepository.findByCurrentLocationIgnoreCaseAndStatus(booking.getSrc(), CarStatus.AVAILABLE.getStatus());
		if(availableCars!=null && !availableCars.isEmpty()) {
		booking.setCarId(availableCars.get(0).getCarId());
		booking.setStatus(BookingStatus.ALLOCATED.getStatus());
		bookingResp=bookingRepository.save(booking);
		Car car=new Car();
		car.setCarId(availableCars.get(0).getCarId());
		car.setStatus(CarStatus.NOT_AVAILABLE.getStatus());
		car.setCurrentLocation(booking.getSrc());
		//Mark car status as not available
		carRepository.save(car);
		}
		return bookingResp;
	}

	public List<Booking> getBookings(int userId) {
		return bookingRepository.findByUserIdAndStatus(userId, BookingStatus.COMPLETED.getStatus());
	}

	public List<Car> getCabs(String currentLocation) {
		return carRepository.findByCurrentLocationIgnoreCaseAndStatus(currentLocation,CarStatus.AVAILABLE.getStatus());
	}
}
