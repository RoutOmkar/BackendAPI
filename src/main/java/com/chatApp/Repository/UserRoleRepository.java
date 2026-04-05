package com.chatApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatApp.Entity.User;
import com.chatApp.Entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	UserRole findByUser(User user);
}