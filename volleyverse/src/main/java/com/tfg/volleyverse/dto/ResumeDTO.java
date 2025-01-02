package com.tfg.volleyverse.dto;

import java.util.UUID;

public class ResumeDTO {

	protected UUID id;
	protected String name;
	
	public ResumeDTO () {
		
	}
	
	public ResumeDTO(UUID id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
