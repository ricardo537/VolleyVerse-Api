package com.tfg.volleyverse.model;

import java.util.UUID;

import com.tfg.volleyverse.dto.RegisterClubDTO;
import com.tfg.volleyverse.dto.UpdateClubDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "club")
public class Club {

	@Id 
	@GeneratedValue (strategy = GenerationType.UUID)
	private UUID id;
	
	@Column (nullable = false, unique = true)
	private String name;
	
	@Column (nullable = false)
	private String zipCode;
	
	@Column (nullable = false)
	private String contact;
	
	@Column (nullable = false)
	private String web;
	
	private Club () {
		
	}
	
	public Club (String name, String zipCode, String contact, String web) {
		this.name = name;
		this.zipCode = zipCode;
		this.contact = contact;
		this.web = web;
	}
	
	public Club (RegisterClubDTO register) {
		this.name = register.getName();
		this.zipCode = register.getZip_code();
		this.contact = register.getContact();
		this.web = "";
	}
	
	public void update (UpdateClubDTO update) {
		if (update.getName() != null && !update.getName().equals("")) {
			this.name = update.getName();
		}
		if (update.getZip_code() != null && !update.getZip_code().equals("")) {
			this.zipCode = update.getZip_code();
		}
		if (update.getContact() != null && !update.getContact().equals("")) {
			this.contact = update.getContact();
		}
		if (update.getWeb() != null && !update.getWeb().equals("")) {
			this.web = update.getWeb();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public UUID getId() {
		return id;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	
}
