package com.wednesdays.BookingPlatform.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CutomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse errorDetails = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), ExceptionResponse.FAILED);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleGenericNotFoundException(NotFoundException ex, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false),
				ExceptionResponse.FAILED);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}