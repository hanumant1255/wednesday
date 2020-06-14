package com.wednesdays.BookingPlatform.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
  private Date timestamp;
  private String message;
  private String details;
	private String status;
}