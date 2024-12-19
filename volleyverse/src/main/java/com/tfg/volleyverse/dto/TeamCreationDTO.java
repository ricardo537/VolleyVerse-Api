package com.tfg.volleyverse.dto;

import java.util.UUID;

import jakarta.persistence.Column;

public class TeamCreationDTO {
	
	private String name;
	private String gender;
	private String category;
	private String type;
	private UUID clubId;
	private LoginDTO login;
	
	public TeamCreationDTO () {
		
	}

	public TeamCreationDTO(String name, String gender, String category, String type, UUID clubId, LoginDTO login) {
		this.name = name;
		this.gender = gender;
		this.category = category;
		this.type = type;
		this.clubId = clubId;
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

	public UUID getClubId() {
		return clubId;
	}

	public void setClubId(UUID clubId) {
		this.clubId = clubId;
	}

	public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
