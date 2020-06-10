package com.wednesdays.BookingPlatform.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Booking {
	@Id
	@GeneratedValue
	private int bookingId;
	private String src;
	private String dest;
	private String status;
	private int userId;
	private int carId;
}
