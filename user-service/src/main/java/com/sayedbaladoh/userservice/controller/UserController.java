package com.sayedbaladoh.userservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sayedbaladoh.userservice.model.User;
import com.sayedbaladoh.userservice.service.UserService;

/**
 * Users Controller
 * 
 * @author SayedBaladoh
 *
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	public User add(@RequestBody User user) {
		return userService.save(user);
	}

	@GetMapping
	public List<User> findAll() {
		return userService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Optional<User> user = userService.get(id);
		if (user.isPresent())
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/site/{siteId}")
	public List<User> findBySite(@PathVariable("siteId") Long siteId) {
		return userService.getBySiteId(siteId);
	}

	@GetMapping("/organization/{organizationId}")
	public List<User> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		return userService.getByOrganizationId(organizationId);
	}

}
