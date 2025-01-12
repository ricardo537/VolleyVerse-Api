package com.tfg.volleyverse.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(PlayId.class)
@Table(name = "play")
public class Play {

	@Id
	@Column(name = "teamId")
	private UUID teamId;
	
	@Id
	@Column(name = "playerId")
	private UUID playerId;

	public Play () {
		
	}
	
	public Play(UUID teamId, UUID playerId) {
		this.teamId = teamId;
		this.playerId = playerId;
	}

	public UUID getTeamId() {
		return teamId;
	}

	public void setTeamId(UUID teamId) {
		this.teamId = teamId;
	}

	public UUID getPlayerId() {
		return playerId;
	}

	public void setPlayerId(UUID playerId) {
		this.playerId = playerId;
	}
	
}
