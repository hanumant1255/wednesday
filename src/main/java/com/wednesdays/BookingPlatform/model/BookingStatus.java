package com.wednesdays.BookingPlatform.model;

public enum BookingStatus {
REQUESTED("REQUESTED"),
ALLOCATED("ALLOCATED"),
CANCELLED("CANCELLED"),
COMPLETED("COMPLETED");
	private String status;
	private BookingStatus(String status) {
		this.status=status;
	}
	public String getStatus() {
		return status;
	}
}
