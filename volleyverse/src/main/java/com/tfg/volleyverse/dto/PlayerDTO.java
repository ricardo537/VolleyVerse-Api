package com.tfg.volleyverse.dto;

import com.tfg.volleyverse.model.Player;


public class PlayerDTO {
	
	private String name;
	private String last_name;
	private String description;
	private String gender;
	private String img;
	
	public PlayerDTO () {
		
	}
	
	public PlayerDTO(String name, String last_name, String description, String gender) {
		this.name = name;
		this.last_name = last_name;
		this.description = description;
		this.gender = gender;
	}



	public PlayerDTO (Player player, String img) {
		this.name = player.getName();
		this.last_name = player.getLastName();
		this.description = player.getDescription();
		this.gender = player.getGender();
		this.img = img;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
