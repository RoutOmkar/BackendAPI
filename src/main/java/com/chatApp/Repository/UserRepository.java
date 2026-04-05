package com.chatApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatApp.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email); // ✅ correct

	boolean existsByEmail(String email); // ✅ correct
}