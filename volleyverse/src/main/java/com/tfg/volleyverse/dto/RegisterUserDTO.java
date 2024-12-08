package com.tfg.volleyverse.dto;

import java.util.UUID;

public class RegisterUserDTO {

	private String email;
	private String password;
	private String type;
	private UUID id_user;
	
	private RegisterUserDTO () {
		
	}

	public RegisterUserDTO(String email, String password, String type, UUID id_user) {
		super();
		this.email = email;
		this.password = password;
		this.type = type;
		this.id_user = id_user;
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
