package com.tfg.volleyverse.dto;

import java.util.UUID;


public class InvitationSendDTO {
	
	private String date;
	private String host;
	private UUID userId;
	private UUID teamId;
	private Boolean state;
	
	public InvitationSendDTO () {
		
	}

	public InvitationSendDTO(String date, String host, UUID userId, UUID teamId, Boolean state) {
		this.date = date;
		this.host = host;
		this.userId = userId;
		this.teamId = teamId;
		this.state = state;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
