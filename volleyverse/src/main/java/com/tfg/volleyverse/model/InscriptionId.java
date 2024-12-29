package com.tfg.volleyverse.model;

import java.io.Serializable;
import java.util.UUID;

public class InscriptionId implements Serializable {
	
	private UUID participantId;
	private UUID eventId;
	
	public InscriptionId () {
		
	}

	public InscriptionId(UUID participantId, UUID eventId) {
		this.participantId = participantId;
		this.eventId = eventId;
	}
	
	public InscriptionId(Inscription inscription) {
		
	}

	public UUID getParticipantId() {
		return participantId;
	}

	public void setParticipantId(UUID participantId) {
		this.participantId = participantId;
	}

	public UUID getEventId() {
		return eventId;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}
}
