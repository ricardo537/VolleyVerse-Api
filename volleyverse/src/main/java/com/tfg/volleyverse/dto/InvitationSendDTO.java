package com.tfg.volleyverse.dto;

import java.util.UUID;


public class InvitationSendDTO {
	
	private UUID userId;
	private UUID teamId;
	private LoginDTO login;
	
	public InvitationSendDTO () {
		
	}

	public InvitationSendDTO(UUID userId, UUID teamId, LoginDTO login) {
		this.userId = userId;
		this.teamId = teamId;
	}

	public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
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
	
}
