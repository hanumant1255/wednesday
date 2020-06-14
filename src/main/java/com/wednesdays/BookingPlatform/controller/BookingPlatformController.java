package com.wednesdays.BookingPlatform.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wednesdays.BookingPlatform.entity.Booking;
import com.wednesdays.BookingPlatform.entity.Car;
import com.wednesdays.BookingPlatform.exception.NotFoundException;
import com.wednesdays.BookingPlatform.model.BookingListReponse;
import com.wednesdays.BookingPlatform.model.BookingResponse;
import com.wednesdays.BookingPlatform.model.CarResponse;
import com.wednesdays.BookingPlatform.model.ResponseStatus;
import com.wednesdays.BookingPlatform.service.BookingPlatformService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class BookingPlatformController {

	
	private final BookingPlatformService bookingPlatformService;

	@PostMapping(value = "/booking")
	public ResponseEntity<BookingResponse> requestBooking(@RequestBody @Valid Booking bookingReq) {
		BookingResponse bookingResponse = new BookingResponse();
			Booking booking = bookingPlatformService.requestBooking(bookingReq);
			ResponseStatus responseStatus = new ResponseStatus();
			if (booking.getBookingId() > 0) {
				responseStatus.setStatus(ResponseStatus.SUCCESS);
			} else {		
	          throw new NotFoundException("No cars available for booking");  			
			}
			bookingResponse.setBooking(booking);
			bookingResponse.setResponseStatus(responseStatus);
			return new ResponseEntity<BookingResponse>(bookingResponse, HttpStatus.CREATED);

	}

	@GetMapping(value = "/booking/{userId}")
	public ResponseEntity<BookingListReponse> getBookings(@PathVariable int userId) {
		BookingListReponse bookingListReponse = new BookingListReponse();
			List<Booking> bookings = bookingPlatformService.getBookings(userId);
			ResponseStatus responseStatus = new ResponseStatus();
			responseStatus.setStatus(ResponseStatus.SUCCESS);
			bookingListReponse.setResponseStatus(responseStatus);
			bookingListReponse.setBookings(bookings);
			return new ResponseEntity<BookingListReponse>(bookingListReponse, HttpStatus.OK);

	}

	@GetMapping(value = "/cabs/{userId}")
	public ResponseEntity<CarResponse> getCabs(@RequestParam String location) {
		CarResponse carResponse = new CarResponse();
			List<Car> cars = bookingPlatformService.getCabs(location);
			ResponseStatus responseStatus = new ResponseStatus();
			responseStatus.setStatus(ResponseStatus.SUCCESS);
			carResponse.setResponseStatus(responseStatus);
			carResponse.setCars(cars);
			return new ResponseEntity<CarResponse>(carResponse, HttpStatus.OK);

	}

}
