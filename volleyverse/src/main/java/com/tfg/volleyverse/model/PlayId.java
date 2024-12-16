package com.tfg.volleyverse.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class PlayId implements Serializable {

	private UUID teamId;
	private UUID playerId;
	
	public PlayId () {
		
	}

	public PlayId(UUID teamId, UUID playerId) {
		this.teamId = teamId;
		this.playerId = playerId;
	}
	
	public PlayId(Play play) {
		this.teamId = play.getTeamId();
		this.playerId = play.getPlayerId();
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

	@Override
	public int hashCode() {
		return Objects.hash(playerId, teamId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayId other = (PlayId) obj;
		return Objects.equals(playerId, other.playerId) && Objects.equals(teamId, other.teamId);
	}
	
	
	
}
