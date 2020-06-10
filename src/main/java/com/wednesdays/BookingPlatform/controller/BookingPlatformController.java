package com.wednesdays.BookingPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wednesdays.BookingPlatform.models.Booking;
import com.wednesdays.BookingPlatform.models.Car;
import com.wednesdays.BookingPlatform.service.BookingPlatformService;

@RestController
public class BookingPlatformController {

	@Autowired
	BookingPlatformService bookingPlatformService;

	@PostMapping(value = "/booking")
	public ResponseEntity<String> requestBooking(@RequestBody Booking booking) {
		try {
			bookingPlatformService.requestBooking(booking);
			return new ResponseEntity<String>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/booking/{userId}")
	public ResponseEntity<List<Booking>> getBookings(@PathVariable int userId) {
		try {
			List<Booking> list = bookingPlatformService.getBookings(userId);
			return new ResponseEntity<List<Booking>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Booking>>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping(value = "/cabs/{userId}")
	public ResponseEntity<List<Car>> getCabs(@RequestParam String location) {
		try {
			List<Car> list = bookingPlatformService.getCabs(location);
			return new ResponseEntity<List<Car>>(list, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<Car>>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
