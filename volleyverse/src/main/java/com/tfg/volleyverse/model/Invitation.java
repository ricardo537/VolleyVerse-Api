package com.tfg.volleyverse.model;

import java.util.UUID;

import com.tfg.volleyverse.dto.InvitationDTO;
import com.tfg.volleyverse.dto.InvitationSendDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "invitation")
public class Invitation {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private UUID host;
	@Column(nullable = false)
	private UUID userId;
	@Column(nullable = false)
	private UUID teamId;
	@Column(nullable = true)
	private Boolean state;
	
	public Invitation () {
		
	}

	public Invitation(UUID host, UUID userId, UUID teamId, Boolean state) {
		this.host = host;
		this.userId = userId;
		this.teamId = teamId;
		this.state = state;
	}
	
	public Invitation(InvitationSendDTO invitation, UUID host) {
		this.host = host;
		this.userId = invitation.getUserId();
		this.teamId = invitation.getTeamId();
	}

	public UUID getHost() {
		return host;
	}

	public void setHost(UUID host) {
		this.host = host;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getTeamId() {
		return teamId;
	}

	public void setTeamId(UUID teamId) {
		this.teamId = teamId;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public UUID getId() {
		return id;
	}
}
