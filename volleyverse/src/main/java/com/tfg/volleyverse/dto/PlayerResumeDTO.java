package com.tfg.volleyverse.dto;

import java.util.UUID;

import com.tfg.volleyverse.model.Player;

public class PlayerResumeDTO {
	
	private UUID id;
	private String name;
	private String last_name;
	private String description;
	private String img;
	
	public PlayerResumeDTO () {
		
	}
	
	public PlayerResumeDTO(UUID id, String name, String last_name, String description, String img) {
		this.id = id;
		this.name = name;
		this.last_name = last_name;
		this.description = description;
		this.img = img;
	}
	
	public PlayerResumeDTO (Player player) {
		this.id = player.getId();
		this.name = player.getName();
		this.last_name = player.getLastName();
		this.description = player.getDescription();
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public UUID getId() {
		return id;
	}
	
}
