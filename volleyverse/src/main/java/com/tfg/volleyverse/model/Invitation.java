package com.tfg.volleyverse.model;

import java.util.UUID;

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
	private String date;
	@Column(nullable = false)
	private String host;
	@Column(nullable = false)
	private UUID userId;
	@Column(nullable = false)
	private UUID teamId;
	@Column(nullable = true)
	private Boolean state;
	
	public Invitation () {
		
	}

	public Invitation(String date, String host, UUID userId, UUID teamId, Boolean state) {
		this.date = date;
		this.host = host;
		this.userId = userId;
		this.teamId = teamId;
		this.state = state;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
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
