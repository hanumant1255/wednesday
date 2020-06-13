package com.wednesdays.BookingPlatform.model;

public enum CarStatus {
	AVAILABLE("AVAILABLE"),
	NOT_AVAILABLE("NOT_AVAILABLE");
	private String status;
	private CarStatus(String status) {
		this.status=status;
	}
	public String getStatus() {
		return status;
	}
}
