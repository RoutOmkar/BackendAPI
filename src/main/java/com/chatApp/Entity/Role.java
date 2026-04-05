package com.chatApp.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private RoleType roleName;

	private String description;

	// Getter & Setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleType getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleType roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// Constructors
	public Role(Long id, RoleType roleName, String description) {
		this.id = id;
		this.roleName = roleName;
		this.description = description;
	}

	public Role() {
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", description=" + description + "]";
	}
}