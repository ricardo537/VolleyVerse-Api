package com.tfg.volleyverse.dto;

import com.tfg.volleyverse.model.User;

public class LoginDTO {

	private String email;
	private String password;
	private String type;
	
	LoginDTO () {
		
	}
	
	public LoginDTO (String email, String password, String type) {
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	public LoginDTO (User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.type = user.getType();
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
	
}
