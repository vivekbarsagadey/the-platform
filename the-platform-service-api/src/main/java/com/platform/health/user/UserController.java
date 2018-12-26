package com.platform.health.user;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/")
	@ApiOperation(value = "Get All users", produces = "application/text")
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<User> save(@RequestBody User newUser) {
		String email = newUser.getEmailId();

		if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			throw new UserException("Password fields do not match", newUser.getId());
		}
		if (null == email || email.isEmpty() || email.isBlank()) {
			throw new UserException("email is mandatory", newUser.getId());
		}
		return new ResponseEntity<User>((userRepository.save(newUser)), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable String id) {
		Optional<User> entity = userRepository.findById(id);
		if (entity.isPresent()) {
			return new ResponseEntity<User>(entity.get(), HttpStatus.OK);
		} else {
			throw new UserException("User not found:", id);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@RequestBody User newUser,
			@PathVariable(name = "id", required = true) String id) {
		return userRepository.findById(id).map(user -> {

			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setEmailId(newUser.getEmailId());
			user.setPhone(newUser.getPhone());
			user.setPassword(newUser.getPassword());
			user.setConfirmPassword(newUser.getPassword());
			return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<User>(newUser, HttpStatus.NOT_FOUND);
		});

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		userRepository.deleteById(id);
		return new ResponseEntity<String>("User deleted successfully!!", HttpStatus.OK);
	}

}