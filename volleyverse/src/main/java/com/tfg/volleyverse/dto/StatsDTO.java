package com.tfg.volleyverse.dto;

import com.tfg.volleyverse.model.Stats;

public class StatsDTO {

	private Double weight;
	private Integer height;
	private Integer jump;
	private Integer resistance;
	private Integer velocity;
	private Integer strength;
	private LoginDTO login;
	
	public StatsDTO () {
		
	}

	public StatsDTO(Double weight, Integer height, Integer jump, Integer resistance, Integer velocity, Integer strength,
			LoginDTO login) {
		super();
		this.weight = weight;
		this.height = height;
		this.jump = jump;
		this.resistance = resistance;
		this.velocity = velocity;
		this.strength = strength;
		this.login = login;
	}

	public StatsDTO(Stats stats) {
		this.weight = stats.getWeight();
		this.height = stats.getHeight();
		this.jump = stats.getJump();
		this.resistance = stats.getResistance();
		this.velocity = stats.getVelocity();
		this.strength = stats.getStrength();
		this.login = new LoginDTO();
	}

	public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
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
