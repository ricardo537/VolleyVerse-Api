package com.tfg.volleyverse.dto;

import com.tfg.volleyverse.model.Club;

public class ClubDTO {
	
	private String name;
	private String zip_code;
	private String contact;
	private String web;
	private String img;
	
	public ClubDTO () {
		
	}

	public ClubDTO(String name, String zip_code, String contact, String web, String img) {
		this.name = name;
		this.zip_code = zip_code;
		this.contact = contact;
		this.web = web;
		this.img = img;
	}
	
	public ClubDTO(Club club, String img) {
		this.name = club.getName();
		this.zip_code = club.getZip_code();
		this.contact = club.getContact();
		this.web = club.getWeb();
		if (img == null) {
			this.img = "";
		} else {
			this.img = img;
		}
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

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
