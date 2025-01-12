package com.tfg.volleyverse.dto;

public class RegisterPlayerDTO {

	private String email;
	private String password;
	private String name;
	private String last_name;
	private String gender;
	
	RegisterPlayerDTO () {
		
	}
	
	RegisterPlayerDTO (String email, String password, String name, String last_name, String gender) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.last_name = last_name;
		this.gender = gender;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
