package com.platform.health.doctor;

public class DoctorException extends RuntimeException {
	public DoctorException(String msg, String id) {
		super(msg + ": " + id);

	}

}
