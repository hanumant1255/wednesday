package com.wednesdays.BookingPlatform.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus {
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";

	private String status;
	private String message;

}
