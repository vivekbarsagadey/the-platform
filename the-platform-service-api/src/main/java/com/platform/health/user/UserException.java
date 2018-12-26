package com.platform.health.user;

public class UserException extends RuntimeException {
	UserException(String msg, String id) {
		super(msg + ": " + id);
	}
}
