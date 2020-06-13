package com.wednesdays.BookingPlatform.model;

import java.util.List;

import com.wednesdays.BookingPlatform.entity.Car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponse {
	private ResponseStatus responseStatus;
	private List<Car> cars;
}
