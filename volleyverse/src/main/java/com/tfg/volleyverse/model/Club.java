package com.tfg.volleyverse.model;

import java.util.UUID;

import com.tfg.volleyverse.dto.RegisterClubDTO;

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
	private String email;
	
	@Column (nullable = false, unique = true)
	private String name;
	
	@Column (nullable = false)
	private String zip_code;
	
	@Column (nullable = false, unique = true)
	private String contact;
	
	private Club () {
		
	}
	
	public Club (String email, String name, String zip_code, String contact) {
		Club club = new Club();
		club.setEmail(email);
		club.setName(name);
		club.setZip_code(zip_code);
		club.setContact(contact);
	}
	
	public Club (RegisterClubDTO register) {
		Club club = new Club();
		club.setEmail(register.getEmail());
		club.setName(register.getName());
		club.setZip_code(register.getZip_code());
		club.setContact(register.getContact());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
