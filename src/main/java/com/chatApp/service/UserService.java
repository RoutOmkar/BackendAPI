package com.chatApp.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.Entity.Role;
import com.chatApp.Entity.RoleType;
import com.chatApp.Entity.User;
import com.chatApp.Entity.UserRole;
import com.chatApp.Entity.UserStatus;
import com.chatApp.Repository.RoleRepository;
import com.chatApp.Repository.UserRepository;
import com.chatApp.Repository.UserRoleRepository;
import com.chatApp.dto.LoginRequest;
import com.chatApp.dto.RegisterRequest;
import com.chatApp.dto.UserResponse;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private UserRoleRepository userRoleRepo;

	// 🔥 REGISTER USER
	public UserResponse register(RegisterRequest request) {

		if (userRepo.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword()); // later BCrypt
		user.setStatus(UserStatus.ACTIVE);
		user.setCreatedAt(LocalDateTime.now());

		User savedUser = userRepo.save(user);

		// 🔥 Assign default role (ADMIN or USER)
		Role role = roleRepo.findByRoleName(RoleType.VIEWER).orElseThrow(() -> new RuntimeException("Role not found"));
		UserRole userRole = new UserRole();
		userRole.setUser(savedUser);
		userRole.setRole(role);
		userRole.setAssignedAt(LocalDateTime.now());

		userRoleRepo.save(userRole);

		return mapToResponse(savedUser);
	}

	// 🔥 LOGIN
	public UserResponse login(LoginRequest request) {

		User user = userRepo.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
		if (!user.getPassword().equals(request.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		return mapToResponse(user);
	}

	// 🔥 Convert Entity → DTO
	private UserResponse mapToResponse(User user) {

		UserResponse res = new UserResponse();
		res.setId(user.getId());
		res.setName(user.getName());
		res.setEmail(user.getEmail());
		res.setStatus(user.getStatus().name());

		// 🔥 ADD THIS PART
		UserRole userRole = userRoleRepo.findByUser(user);
		res.setRole(userRole.getRole().getRoleName().name());

		return res;
	}
}