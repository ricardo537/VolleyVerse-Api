package com.tfg.volleyverse.dto;

public class RegisterClubDTO {

	private String email;
	private String password;
	private String name;
	private String zip_code;
	private String contact;
	
	RegisterClubDTO () {
		
	}
	
	RegisterClubDTO (String email, String password, String name, String zip_code, String contact) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.zip_code = zip_code;
		this.contact = contact;
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
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
