package com.tfg.volleyverse.dto;

import java.util.UUID;

public class AddPlayerToTeamDTO {

	private UUID teamId;
	private UUID playerId;
	private LoginDTO login;
	
	public AddPlayerToTeamDTO () {
		
	}

	public AddPlayerToTeamDTO(UUID teamId, UUID playerId, LoginDTO login) {
		this.teamId = teamId;
		this.playerId = playerId;
		this.login = login;
	}

	public UUID getTeamId() {
		return teamId;
	}

	public void setTeamId(UUID teamId) {
		this.teamId = teamId;
	}

	public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
	}

	public UUID getPlayerId() {
		return playerId;
	}

	public void setPlayerId(UUID playerId) {
		this.playerId = playerId;
	}
	
}
