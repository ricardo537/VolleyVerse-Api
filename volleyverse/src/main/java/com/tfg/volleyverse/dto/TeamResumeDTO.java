package com.tfg.volleyverse.dto;

import java.util.List;
import java.util.UUID;

import com.tfg.volleyverse.model.Team;

public class TeamResumeDTO {

	private UUID id;
	private String name;
	private List<PlayerResumeDTO> members;
	
	public TeamResumeDTO () {
		
	}

	public TeamResumeDTO(UUID id, String name, List<PlayerResumeDTO> members) {
		this.id = id;
		this.name = name;
		this.members = members;
	}
	
	public TeamResumeDTO(Team team, List<PlayerResumeDTO> members) {
		this.id = team.getId();
		this.name = team.getName();
		this.members = members;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PlayerResumeDTO> getMembers() {
		return members;
	}

	public void setMembers(List<PlayerResumeDTO> members) {
		this.members = members;
	}
}
