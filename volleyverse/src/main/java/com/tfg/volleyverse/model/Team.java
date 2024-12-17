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
	private UUID clubId;
	
	public Team () {
		
	}

	public Team(String name, String category, String type, UUID clubId) {
		this.name = name;
		this.category = category;
		this.type = type;
		this.clubId = clubId;
	}
	
	public Team(TeamCreationDTO team) {
		this.name = team.getName();
		this.category = team.getCategory();
		this.type = team.getType();
		this.clubId = team.getClubId();
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

	public UUID getClubId() {
		return clubId;
	}

	public void setClubId(UUID clubId) {
		this.clubId = clubId;
	}

	public UUID getId() {
		return id;
	}
}
