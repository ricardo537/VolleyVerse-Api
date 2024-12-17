package com.tfg.volleyverse.dto;

import java.util.List;
import java.util.UUID;

import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.model.Team;

public class TeamDTO {

	private UUID id;
	private String name;
	private String category;
	private String type;
	private UUID club_id;
	private List<Player> members;
	
	public TeamDTO() {
		
	}

	public TeamDTO(UUID id, String name, String category, String type, UUID club_id, List<Player> members) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.type = type;
		this.club_id = club_id;
		this.members = members;
	}
	
	public TeamDTO(Team team, List<Player> members) {
		this.id = team.getId();
		this.name = team.getName();
		this.category = team.getCategory();
		this.type = team.getType();
		this.club_id = team.getClub_id();
		this.members = members;
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

	public List<Player> getMembers() {
		return members;
	}

	public void setMembers(List<Player> members) {
		this.members = members;
	}

	public UUID getId() {
		return id;
	}
}
