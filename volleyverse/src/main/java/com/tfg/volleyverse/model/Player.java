package com.tfg.volleyverse.model;

import java.util.UUID;

import com.tfg.volleyverse.dto.RegisterPlayerDTO;
import com.tfg.volleyverse.dto.UpdatePlayerDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "player")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private String gender;

	private Player () {
		
	}
	
	public Player (String name, String lastName, String description, String gender) {
		this.name = name;
		this.lastName = lastName;
		this.description = description;
		this.gender = gender;
	}
	
	public Player (RegisterPlayerDTO register) {
		this.name = register.getName();
		this.lastName = register.getLast_name();
		this.description = "";
		this.gender = register.getGender();
	}
	
	public void update (UpdatePlayerDTO update) {
		if (update.getName() != null && !update.getName().equals("")) {
			this.name = update.getName();
		}
		if (update.getLast_name() != null && !update.getLast_name().equals("")) {
			this.lastName = update.getLast_name();
		}
		if (update.getDescription() != null) {
			this.description = update.getDescription();
		}
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
