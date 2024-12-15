package com.tfg.volleyverse.dto;

import java.util.UUID;

import jakarta.persistence.Column;

public class TeamCreationDTO {
	
	private String name;
	private String category;
	private String type;
	private UUID club_id;
	private LoginDTO login;
	
	public TeamCreationDTO () {
		
	}

	public TeamCreationDTO(String name, String category, String type, UUID club_id, LoginDTO login) {
		this.name = name;
		this.category = category;
		this.type = type;
		this.club_id = club_id;
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UUID getClub_id() {
		return club_id;
	}

	public void setClub_id(UUID club_id) {
		this.club_id = club_id;
	}

	public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
	}
	
}
