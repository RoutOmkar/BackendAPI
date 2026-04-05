package com.chatApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatApp.dto.LoginRequest;
import com.chatApp.dto.RegisterRequest;
import com.chatApp.dto.UserResponse;
import com.chatApp.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	// 🔥 REGISTER
	@PostMapping("/register")
	public UserResponse register(@RequestBody RegisterRequest request) {
		return userService.register(request);
	}

	// 🔥 LOGIN
	@PostMapping("/login")
	public UserResponse login(@RequestBody LoginRequest request) {
		return userService.login(request);
	}
}