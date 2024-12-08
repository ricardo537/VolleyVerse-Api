package com.tfg.volleyverse.model;

import java.util.UUID;

import com.tfg.volleyverse.dto.RegisterUserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private UUID id_user;
	
	private User () {
		
	}

	public User(String email, String password, String type, UUID id_user) {
		super();
		this.email = email;
		this.password = password;
		this.type = type;
		this.id_user = id_user;
	}
	
	public User(RegisterUserDTO register) {
		this.email = register.getEmail();
		this.password = register.getPassword();
		this.type = register.getType();
		this.id_user = register.getId_user();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UUID getId_user() {
		return id_user;
	}

	public void setId_user(UUID id_user) {
		this.id_user = id_user;
	}
	
}
