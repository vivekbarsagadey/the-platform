package com.platform.health.user;

public class UserException extends RuntimeException {
	public UserException(String msg, String id) {
		super(msg + ": " + id);

	}

}
