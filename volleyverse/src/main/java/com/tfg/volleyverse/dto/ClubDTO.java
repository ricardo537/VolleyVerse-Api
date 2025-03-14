package com.tfg.volleyverse.dto;

import com.tfg.volleyverse.model.Club;

public class ClubDTO {
	
	private String name;
	private String zip_code;
	private String contact;
	
	public ClubDTO () {
		
	}

	public ClubDTO(String name, String zip_code, String contact) {
		this.name = name;
		this.zip_code = zip_code;
		this.contact = contact;
	}
	
	public ClubDTO(Club club) {
		this.name = club.getName();
		this.zip_code = club.getZip_code();
		this.contact = club.getContact();
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
