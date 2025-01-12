package com.tfg.volleyverse.dto;

import java.util.List;
import java.util.UUID;

import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.model.Team;

public class TeamDTO {

	private UUID id;
	private String name;
	private String category;
	private String gender;
	private String type;
	private UUID clubId;
	private List<PlayerResumeDTO> members;
	
	public TeamDTO() {
		
	}

	public TeamDTO(UUID id, String name, String category, String gender, String type, UUID clubId, List<PlayerResumeDTO> members) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.gender = gender;
		this.type = type;
		this.clubId = clubId;
		this.members = members;
	}
	
	public TeamDTO(Team team, List<PlayerResumeDTO> members) {
		this.id = team.getId();
		this.name = team.getName();
		this.category = team.getCategory();
		this.gender = team.getGender();
		this.type = team.getType();
		this.clubId = team.getClubId();
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public List<PlayerResumeDTO> getMembers() {
		return members;
	}

	public void setMembers(List<PlayerResumeDTO> members) {
		this.members = members;
	}

	public UUID getId() {
		return id;
	}
}
