package com.tfg.volleyverse.dto;

import java.util.UUID;

public class ParticipantsDTO {

	private UUID eventId;
	private LoginDTO login;
	
	public ParticipantsDTO () {
		
	}

	public ParticipantsDTO(UUID eventId, LoginDTO login) {
		this.eventId = eventId;
		this.login = login;
	}

	public UUID getEventId() {
		return eventId;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}

	public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
	}
}
