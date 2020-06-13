package com.wednesdays.BookingPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wednesdays.BookingPlatform.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{
//	   @Query(
//		        value = "SELECT * FROM car where upper(currentLocation)=upper(:currentLocation) AND status = :status", 
//		        nativeQuery=true
//		    )
	List<Car> findByCurrentLocationIgnoreCaseAndStatus(String currentLocation, String status);
	List<Car> findByCurrentLocationIgnoreCaseContaining(String currentLocation);

}
