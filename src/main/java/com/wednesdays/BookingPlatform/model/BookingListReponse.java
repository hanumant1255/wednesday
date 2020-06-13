package com.wednesdays.BookingPlatform.model;

import java.util.List;

import com.wednesdays.BookingPlatform.entity.Booking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingListReponse {
	private ResponseStatus responseStatus;
	private List<Booking> bookings;
}
