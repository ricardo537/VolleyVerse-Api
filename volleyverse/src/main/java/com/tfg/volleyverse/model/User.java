package com.tfg.volleyverse.model;

import java.util.UUID;

import com.tfg.volleyverse.dto.RegisterUserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String last_name;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private double level;

	private User () {
		
	}
	
	public User (String email, String password, String name, String last_name, String description, double level) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.last_name = last_name;
		this.description = description;
		this.level = level;
	}
	
	public User (RegisterUserDTO register) {
		this.email = register.getEmail();
		this.password = register.getPassword();
		this.name = register.getName();
		this.last_name = register.getLast_name();
		this.description = "";
		this.level = 0;
	}
	
	public UUID getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}
	
	
}
