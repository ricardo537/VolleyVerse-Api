package com.tfg.volleyverse.dto;

import java.time.LocalDateTime;

public class FilterEventDTO {

	private LocalDateTime startDate;
	private String type;
	private String category;
	private String gender;
	private String typeParticipant;
	private LoginDTO login;
	
	public FilterEventDTO () {
		
	}

	public FilterEventDTO(LocalDateTime startDate, String type, String category, String gender, String typeParticipant, LoginDTO login) {
		this.startDate = startDate;
		this.type = type;
		this.category = category;
		this.gender = gender;
		this.typeParticipant = typeParticipant;
		this.login = login;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTypeParticipant() {
		return typeParticipant;
	}

	public void setTypeParticipant(String typeParticipant) {
		this.typeParticipant = typeParticipant;
	}

	public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
	}
	
}
