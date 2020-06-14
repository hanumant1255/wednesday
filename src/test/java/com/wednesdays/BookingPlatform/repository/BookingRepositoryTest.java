package com.wednesdays.BookingPlatform.repository;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wednesdays.BookingPlatform.entity.Booking;

@SpringBootTest
public class BookingRepositoryTest {

	@Autowired
	private BookingRepository bookingRepository;

	@Test
	void findByUserIdAndStatus() {
		List<Booking> bookings = Lists.newArrayList(bookingRepository.findByUserIdAndStatus(2, "COMPLETED"));
		Assertions.assertEquals(0, bookings.size(), "Expected 0 booking in database");
	}
}
