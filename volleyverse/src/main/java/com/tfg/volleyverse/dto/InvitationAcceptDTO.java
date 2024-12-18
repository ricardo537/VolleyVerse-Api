package com.tfg.volleyverse.dto;

import java.util.UUID;

public class InvitationAcceptDTO {

	private UUID id;
	private Boolean state;
	private LoginDTO login;
	
	public InvitationAcceptDTO () {
		
	}

	public InvitationAcceptDTO(UUID id, Boolean state, LoginDTO login) {
		this.id = id;
		this.state = state;
		this.login = login;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
}
