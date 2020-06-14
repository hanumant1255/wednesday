package com.wednesdays.BookingPlatform.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.wednesdays.BookingPlatform.entity.Booking;
import com.wednesdays.BookingPlatform.entity.Car;
import com.wednesdays.BookingPlatform.repository.CarRepository;
import com.wednesdays.BookingPlatform.service.BookingPlatformService;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(BookingPlatformController.class)
public class BookingPlatformControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<Booking> jsonBooking;
	@Mock
	Booking booking;

	@MockBean
	BookingPlatformService bookingPlatformService;

	@Test
	public void shouldThrowNotFoundException() throws Exception {
		when(bookingPlatformService.requestBooking(ArgumentMatchers.anyObject())).thenReturn(booking);
		MockHttpServletResponse response = mvc
				.perform(post("/api/v1/booking").contentType(MediaType.APPLICATION_JSON)
						.content(jsonBooking.write(new Booking(1, "pune", "solapur", "REQUESTED", 1, 1)).getJson()))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void shouldCreateNewBooking() throws Exception {
		Booking booking = new Booking();
		booking.setBookingId(1);
		when(bookingPlatformService.requestBooking(ArgumentMatchers.anyObject())).thenReturn(booking);
		MockHttpServletResponse response = mvc
				.perform(post("/api/v1/booking").contentType(MediaType.APPLICATION_JSON)
						.content(jsonBooking.write(new Booking(1, "pune", "solapur", "REQUESTED", 1, 1)).getJson()))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	public void shouldRetriveBookings() throws Exception {
		List<Booking> list = new ArrayList<Booking>();
		when(bookingPlatformService.getBookings(ArgumentMatchers.anyInt())).thenReturn(list);
		MockHttpServletResponse response = mvc.perform(get("/api/v1/booking/1")).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void shouldRetriveCabs() throws Exception {
		List<Car> list = new ArrayList<>();
		when(bookingPlatformService.getCabs(ArgumentMatchers.anyString())).thenReturn(list);
		MockHttpServletResponse response = mvc.perform(get("/api/v1/cabs/1?location=pune")).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
}
