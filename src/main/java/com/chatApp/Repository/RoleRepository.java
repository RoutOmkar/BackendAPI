package com.chatApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatApp.Entity.Role;
import com.chatApp.Entity.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleName(RoleType roleName);
}