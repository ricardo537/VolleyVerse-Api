package com.tfg.volleyverse.dto;

public class FilterTeamDTO {

	private String type;
	private String category;
	private String gender;
	private LoginDTO login;
	
	public FilterTeamDTO () {
		
	}

	public FilterTeamDTO(String type, String category, String gender, LoginDTO login) {
		super();
		this.type = type;
		this.category = category;
		this.gender = gender;
		this.login = login;
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

	public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
	}
}
