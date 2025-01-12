package com.tfg.volleyverse.model;

import java.io.Serializable;
import java.util.Objects;
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

	@Override
	public int hashCode() {
		return Objects.hash(eventId, participantId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InscriptionId other = (InscriptionId) obj;
		return Objects.equals(eventId, other.eventId) && Objects.equals(participantId, other.participantId);
	}
	
}
