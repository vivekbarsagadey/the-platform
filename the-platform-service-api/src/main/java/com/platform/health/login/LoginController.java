package com.platform.health.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.health.user.UserException;
import com.platform.health.user.UserRepository;

@RestController
@RequestMapping("api/login")
public class LoginController {
	private final UserRepository userRepository;

	public LoginController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/{emailId}/{password}")
	public ResponseEntity<String> validate(@PathVariable String emailId, @PathVariable String password) {
		var row = userRepository.findByEmailId(emailId.trim());
		if (!row.isPresent()) {
			throw new UserException("User not found:", emailId);
		}

		if (row.get().getPassword().equals(password)) {
			return new ResponseEntity<String>("Login Successful!!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Wrong Password!!", HttpStatus.UNAUTHORIZED);
	}
}
