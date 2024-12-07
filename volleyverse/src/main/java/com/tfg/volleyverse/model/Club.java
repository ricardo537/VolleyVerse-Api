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
	private String zip_code;
	
	@Column (nullable = false, unique = true)
	private String contact;
	
	private Club () {
		
	}
	
	public Club (String name, String zip_code, String contact) {
		this.name = name;
		this.zip_code = zip_code;
		this.contact = contact;
	}
	
	public Club (RegisterClubDTO register) {
		this.name = register.getName();
		this.zip_code = register.getZip_code();
		this.contact = register.getContact();
	}
	
	public void update (UpdateClubDTO update) {
		if (update.getName() != null && !update.getName().equals("")) {
			this.name = update.getName();
		}
		if (update.getZip_code() != null && !update.getZip_code().equals("")) {
			this.zip_code = update.getZip_code();
		}
		if (update.getContact() != null && !update.getContact().equals("")) {
			this.contact = update.getContact();
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

	public UUID getId() {
		return id;
	}
	
	
}
