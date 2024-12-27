package com.tfg.volleyverse.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tfg.volleyverse.model.Event;

public class MyEventDTO {

	private UUID id;
	private String name;
	private String description;
	private String address;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String type;
	private Integer minParticipants;
	private Integer maxParticipants;
	private Double price;
	private String category;
	private String gender;
	private String typeParticipant;
	private Boolean results;
	
	public MyEventDTO () {
		
	}

	public MyEventDTO(UUID id, String name, String description, String address, LocalDateTime startDate,
			LocalDateTime endDate, String type, Integer minParticipants, Integer maxParticipants, Double price,
			String category, String gender, String typeParticipant, Boolean results) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.minParticipants = minParticipants;
		this.maxParticipants = maxParticipants;
		this.price = price;
		this.category = category;
		this.gender = gender;
		this.typeParticipant = typeParticipant;
		this.results = results;
	}
	
	public MyEventDTO(Event event) {
		this.id = event.getId();
		this.name = event.getName();
		this.description = event.getDescription();
		this.address = event.getAddress();
		this.startDate = event.getStartDate();
		this.endDate = event.getEndDate();
		this.type = event.getType();
		this.minParticipants = event.getMinParticipants();
		this.maxParticipants = event.getMaxParticipants();
		this.price = event.getPrice();
		this.category = event.getCategory();
		this.gender = event.getGender();
		this.typeParticipant = event.getTypeParticipant();
		this.results = event.getResults();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getMinParticipants() {
		return minParticipants;
	}

	public void setMinParticipants(Integer minParticipants) {
		this.minParticipants = minParticipants;
	}

	public Integer getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(Integer maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTypeParticipant() {
		return typeParticipant;
	}

	public void setTypeParticipant(String typeParticipant) {
		this.typeParticipant = typeParticipant;
	}

	public Boolean getResults() {
		return results;
	}

	public void setResults(Boolean results) {
		this.results = results;
	}
	
}
