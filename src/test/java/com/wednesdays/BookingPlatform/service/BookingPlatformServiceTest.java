package com.wednesdays.BookingPlatform.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.wednesdays.BookingPlatform.entity.Booking;
import com.wednesdays.BookingPlatform.entity.Car;
import com.wednesdays.BookingPlatform.repository.BookingRepository;
import com.wednesdays.BookingPlatform.repository.CarRepository;

@SpringBootTest
public class BookingPlatformServiceTest {

	@Autowired
	BookingPlatformService bookingPlatformService;

	@MockBean
	BookingRepository bookingRepository;

	@MockBean
	CarRepository carRepository;

	@Test
	public void shouldNotCreateNewBooking() throws Exception {
		Booking booking = new Booking();
		booking.setBookingId(1);
		booking.setSrc("pune");
		List<Car> cars = new ArrayList<Car>();
		when(carRepository.findByCurrentLocationIgnoreCaseAndStatus(booking.getSrc(),
				"AVAILABLE")).thenReturn(cars);
		Booking bookingResponse = bookingPlatformService.requestBooking(booking);
		assertThat(bookingResponse.getBookingId()).isEqualTo(0);
	}

	@Test
	public void shouldCreateNewBooking() throws Exception {
		Booking booking = new Booking();
		booking.setBookingId(1);
		booking.setSrc("pune");
		Car car = new Car();
		car.setCarId(1);
		List<Car> cars = new ArrayList<Car>();
		cars.add(car);
		when(carRepository.findByCurrentLocationIgnoreCaseAndStatus(ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString())).thenReturn(cars);
		when(bookingRepository.save(ArgumentMatchers.anyObject())).thenReturn(booking);
		Booking bookingResponse = bookingPlatformService.requestBooking(booking);
		assertThat(bookingResponse.getBookingId()).isEqualTo(1);
	}
}
