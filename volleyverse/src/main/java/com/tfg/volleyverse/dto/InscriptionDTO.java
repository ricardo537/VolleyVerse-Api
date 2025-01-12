package com.tfg.volleyverse.dto;

import java.util.UUID;

public class InscriptionDTO {
	private UUID eventId;
	private UUID participantId;
	private LoginDTO login;
	
	public InscriptionDTO () {
		
	}

	public InscriptionDTO(UUID eventId, UUID participantId, LoginDTO login) {
		super();
		this.eventId = eventId;
		this.participantId = participantId;
		this.login = login;
	}

	public UUID getEventId() {
		return eventId;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}

	public UUID getParticipantId() {
		return participantId;
	}

	public void setParticipantId(UUID participantId) {
		this.participantId = participantId;
	}

	public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
	}
}
