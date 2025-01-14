package com.tfg.volleyverse.model;

import java.util.UUID;

import com.tfg.volleyverse.dto.StatsDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stats")
public class Stats {

	@Id
	private UUID playerId;
	
	@Column(nullable = false)
	private Double weight;
	
	@Column(nullable = false)
	private Integer height;
	
	@Column(nullable = false)
	private Integer jump;
	
	@Column(nullable = false)
	private Integer resistance;
	
	@Column(nullable = false)
	private Integer velocity;
	
	@Column(nullable = false)
	private Integer strength;
	
	public Stats () {
		
	}

	public Stats(UUID playerId, Double weight, Integer height, Integer jump, Integer resistance, Integer velocity,
			Integer strength) {
		this.playerId = playerId;
		this.weight = weight;
		this.height = height;
		this.jump = jump;
		this.resistance = resistance;
		this.velocity = velocity;
		this.strength = strength;
	}

	public Stats(StatsDTO stats, UUID playerId) {
		this.playerId = playerId;
		this.weight = stats.getWeight();
		this.height = stats.getHeight();
		this.jump = stats.getJump();
		this.resistance = stats.getResistance();
		this.velocity = stats.getVelocity();
		this.strength = stats.getStrength();
	}

	public UUID getPlayerId() {
		return playerId;
	}

	public void setPlayerId(UUID playerId) {
		this.playerId = playerId;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getJump() {
		return jump;
	}

	public void setJump(Integer jump) {
		this.jump = jump;
	}

	public Integer getResistance() {
		return resistance;
	}

	public void setResistance(Integer resistance) {
		this.resistance = resistance;
	}

	public Integer getVelocity() {
		return velocity;
	}

	public void setVelocity(Integer velocity) {
		this.velocity = velocity;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

}
