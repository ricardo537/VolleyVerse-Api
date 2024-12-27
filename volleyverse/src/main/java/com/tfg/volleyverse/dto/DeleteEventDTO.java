package com.tfg.volleyverse.dto;

import java.util.UUID;

public class DeleteEventDTO {

	private UUID id;
	private LoginDTO login;
	
	public DeleteEventDTO () {
		
	}

	public DeleteEventDTO(UUID id, LoginDTO login) {
		this.id = id;
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
}
