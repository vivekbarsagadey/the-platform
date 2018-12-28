package com.platform.health.hospital;

public class HospitalException extends RuntimeException {
	public HospitalException(String msg, String id) {
		super(msg + ": " + id);

	}

}
