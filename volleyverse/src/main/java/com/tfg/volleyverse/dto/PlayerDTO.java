package com.tfg.volleyverse.dto;

import com.tfg.volleyverse.model.Player;


public class PlayerDTO {
	
	private String name;
	private String last_name;
	private String description;
	private double level;
	
	public PlayerDTO () {
		
	}
	
	public PlayerDTO(String name, String last_name, String description, double level) {
		this.name = name;
		this.last_name = last_name;
		this.description = description;
		this.level = level;
	}



	public PlayerDTO (Player player) {
		this.name = player.getName();
		this.last_name = player.getLast_name();
		this.description = player.getDescription();
		this.level = player.getLevel();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}
	
}
