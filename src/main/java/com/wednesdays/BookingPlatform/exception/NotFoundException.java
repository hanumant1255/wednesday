package com.wednesdays.BookingPlatform.exception;

public class NotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -265791634088503495L;

	public NotFoundException(String message) {
		super(message);
	}
}
