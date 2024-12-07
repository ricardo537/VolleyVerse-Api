package com.tfg.volleyverse.dto;

import com.tfg.volleyverse.model.Player;

import jakarta.persistence.Column;

public class PlayerDTO {
	
	private String email;
	private String password;
	private String name;
	private String last_name;
	private String description;
	private double level;
	
	public PlayerDTO () {
		
	}
	
	public PlayerDTO(String email, String password, String name, String last_name, String description, double level) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.last_name = last_name;
		this.description = description;
		this.level = level;
	}



	public PlayerDTO (Player user) {
		this.name = user.getName();
		this.last_name = user.getLast_name();
		this.description = user.getDescription();
		this.level = user.getLevel();
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
