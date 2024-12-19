package com.tfg.volleyverse.dto;

import java.util.UUID;

public class LeaveTeamDTO {
	
	private UUID teamId;
	private LoginDTO login;
	
	public LeaveTeamDTO () {
		
	}

	public LeaveTeamDTO(UUID teamId, LoginDTO login) {
		super();
		this.teamId = teamId;
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
}
