package com.wednesdays.BookingPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wednesdays.BookingPlatform.models.Car;

public interface CarRepository extends JpaRepository<Car, Long>{
	List<Car> findByCurrentLocationAndStatus(String currentLocation, String status);

}
