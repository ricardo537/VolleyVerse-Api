package com.tfg.volleyverse.model;

import java.util.UUID;

import com.tfg.volleyverse.dto.InscriptionDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(InscriptionId.class)
@Table(name = "inscription")
public class Inscription {
	@Id
	@Column(name = "participantId")
	private UUID participantId;
	
	@Id
	@Column(name = "eventId")
	private UUID eventId;
	
	public Inscription () {
		
	}

	public Inscription(UUID participantId, UUID eventId) {
		this.participantId = participantId;
		this.eventId = eventId;
	}
	
	public Inscription(InscriptionDTO inscription) {
		this.participantId = inscription.getParticipantId();
		this.eventId = inscription.getEventId();
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
