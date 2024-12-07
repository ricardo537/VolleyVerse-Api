package com.tfg.volleyverse.dto;

import jakarta.persistence.Column;

public class UpdatePlayerDTO {
	
	private String email;
	private String password;
	private String name;
	private String last_name;
	private String description;
	private LoginDTO login;
	
	UpdatePlayerDTO () {
		
	}
	
	public UpdatePlayerDTO (String email, String password, String name, String last_name, String description, LoginDTO login) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.last_name = last_name;
		this.description = description;
		this.login = login;
	}

	public LoginDTO getLogin() {
		return login;
	}
	
	public void setLogin(LoginDTO login) {
		this.login = login;
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

}
