package com.tfg.volleyverse.dto;

import java.util.UUID;

import com.tfg.volleyverse.model.Invitation;

public class InvitationDTO {
	private UUID id;
	private String host;
	private UUID userId;
	private UUID teamId;
	private String teamName;
	private Boolean state;
	
	public InvitationDTO () {
		
	}

	public InvitationDTO(UUID id, String host, UUID userId, UUID teamId, String teamName, Boolean state) {
		this.id = id;
		this.host = host;
		this.userId = userId;
		this.teamId = teamId;
		this.teamName = teamName;
		this.state = state;
	}

	public InvitationDTO(Invitation invitation, String host, String teamName) {
		this.id = invitation.getId();
		this.host = host;
		this.userId = invitation.getUserId();
		this.teamId = invitation.getTeamId();
		this.teamName = teamName;
		this.state = invitation.getState();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getTeamId() {
		return teamId;
	}

	public void setTeamId(UUID teamId) {
		this.teamId = teamId;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	
	
}
