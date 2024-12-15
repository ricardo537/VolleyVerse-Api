package com.tfg.volleyverse.model;

import java.util.UUID;

import com.tfg.volleyverse.dto.TeamCreationDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "team")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String category;
	@Column(nullable = false)
	private String type;
	@Column(nullable = true)
	private UUID club_id;
	
	public Team () {
		
	}

	public Team(String name, String category, String type, UUID club_id) {
		this.name = name;
		this.category = category;
		this.type = type;
		this.club_id = club_id;
	}
	
	public Team(TeamCreationDTO team) {
		this.name = team.getName();
		this.category = team.getCategory();
		this.type = team.getType();
		this.club_id = team.getClub_id();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UUID getClub_id() {
		return club_id;
	}

	public void setClub_id(UUID club_id) {
		this.club_id = club_id;
	}

	public UUID getId() {
		return id;
	}
}
