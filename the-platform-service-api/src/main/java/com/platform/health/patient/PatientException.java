package com.platform.health.patient;

public class PatientException extends RuntimeException {
	public PatientException(String msg, String id) {
		super(msg + ": " + id);

	}

}
