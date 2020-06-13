package com.wednesdays.BookingPlatform.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wednesdays.BookingPlatform.entity.Booking;
import com.wednesdays.BookingPlatform.entity.Car;
import com.wednesdays.BookingPlatform.model.BookingListReponse;
import com.wednesdays.BookingPlatform.model.BookingResponse;
import com.wednesdays.BookingPlatform.model.CarResponse;
import com.wednesdays.BookingPlatform.model.ResponseStatus;
import com.wednesdays.BookingPlatform.service.BookingPlatformService;

@RestController
public class BookingPlatformController {

	@Autowired
	BookingPlatformService bookingPlatformService;

	@PostMapping(value = "/booking")
	public ResponseEntity<BookingResponse> requestBooking(@RequestBody @Valid Booking bookingReq) {
		BookingResponse bookingResponse = new BookingResponse();
		try {
			Booking booking = bookingPlatformService.requestBooking(bookingReq);
			ResponseStatus responseStatus = new ResponseStatus();
			if (booking.getBookingId() > 0) {
				responseStatus.setStatus(ResponseStatus.SUCCESS);
			} else {
				responseStatus.setStatus(ResponseStatus.FAILED);
				responseStatus.setMessage("No cars available");
			}
			bookingResponse.setBooking(booking);
			bookingResponse.setResponseStatus(responseStatus);
			return new ResponseEntity<BookingResponse>(bookingResponse, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<BookingResponse>(bookingResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/booking/{userId}")
	public ResponseEntity<BookingListReponse> getBookings(@PathVariable int userId) {
		BookingListReponse bookingListReponse = new BookingListReponse();
		try {
			List<Booking> bookings = bookingPlatformService.getBookings(userId);
			ResponseStatus responseStatus = new ResponseStatus();
			responseStatus.setStatus(ResponseStatus.SUCCESS);
			bookingListReponse.setResponseStatus(responseStatus);
			bookingListReponse.setBookings(bookings);
			return new ResponseEntity<BookingListReponse>(bookingListReponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<BookingListReponse>(bookingListReponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/cabs/{userId}")
	public ResponseEntity<CarResponse> getCabs(@RequestParam String location) {
		CarResponse carResponse = new CarResponse();
		try {
			List<Car> cars = bookingPlatformService.getCabs(location);
			ResponseStatus responseStatus = new ResponseStatus();
			responseStatus.setStatus(ResponseStatus.SUCCESS);
			carResponse.setResponseStatus(responseStatus);
			carResponse.setCars(cars);
			return new ResponseEntity<CarResponse>(carResponse, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<CarResponse>(carResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
