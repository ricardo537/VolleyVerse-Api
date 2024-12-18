package com.tfg.volleyverse.dto;

import java.util.UUID;

import com.tfg.volleyverse.model.Invitation;

public class InvitationDTO {
	private UUID id;
	private String host;
	private UUID userId;
	private UUID teamId;
	private Boolean state;
	
	public InvitationDTO () {
		
	}

	public InvitationDTO(UUID id, String host, UUID userId, UUID teamId, Boolean state) {
		this.id = id;
		this.host = host;
		this.userId = userId;
		this.teamId = teamId;
		this.state = state;
	}

	public InvitationDTO(Invitation invitation, String host) {
		this.id = invitation.getId();
		this.host = host;
		this.userId = invitation.getUserId();
		this.teamId = invitation.getTeamId();
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

	
}
