package com.wednesdays.BookingPlatform.model;

import com.wednesdays.BookingPlatform.entity.Booking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponse {

	private ResponseStatus responseStatus;
	private Booking booking;
	
}
