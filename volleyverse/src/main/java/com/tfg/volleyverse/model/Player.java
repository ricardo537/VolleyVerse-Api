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
	private String last_name;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private double level;

	private Player () {
		
	}
	
	public Player (String name, String last_name, String description, double level) {
		this.name = name;
		this.last_name = last_name;
		this.description = description;
		this.level = level;
	}
	
	public Player (RegisterPlayerDTO register) {
		this.name = register.getName();
		this.last_name = register.getLast_name();
		this.description = "";
		this.level = 0;
	}
	
	public void update (UpdatePlayerDTO update) {
		if (update.getName() != null && !update.getName().equals("")) {
			this.name = update.getName();
		}
		if (update.getLast_name() != null && !update.getLast_name().equals("")) {
			this.last_name = update.getLast_name();
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

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}
	
	
}
