package com.tfg.volleyverse.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

public class UpdateEventDTO {

	private UUID id;
	private String name;
	private String description;
	private String address;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime startDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime endDate;
	private String type;
	private Integer minParticipants;
	private Integer maxParticipants;
	private Double price;
	private String category;
	private String gender;
	private String typeParticipant;
	private Boolean results;
	private LoginDTO login;
	
	public UpdateEventDTO () {
		
	}

	public UpdateEventDTO(UUID id, String name, String description, String address, LocalDateTime startDate,
			LocalDateTime endDate, String type, Integer minParticipants, Integer maxParticipants, Double price,
			String category, String gender, String typeParticipant, Boolean results, LoginDTO login) {
		super();
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
		this.login = login;
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

	public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
	}
	
}
