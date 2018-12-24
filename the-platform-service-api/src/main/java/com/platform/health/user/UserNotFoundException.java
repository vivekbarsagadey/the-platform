package com.platform.health.user;

public class UserNotFoundException extends RuntimeException {
	UserNotFoundException(String id) {
		super("could not find user " + id);
	}
}
