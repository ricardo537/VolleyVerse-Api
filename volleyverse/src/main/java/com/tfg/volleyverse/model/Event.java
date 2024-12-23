package com.tfg.volleyverse.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tfg.volleyverse.dto.EventRegisterDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "event")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private LocalDateTime startDate;
	
	@Column(nullable = false)
	private LocalDateTime endDate;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private Integer minParticipants;
	
	@Column(nullable = false)
	private Integer maxParticipants;
	
	@Column(nullable = false)
	private UUID creatorId;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = true)
	private String category;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private String typeParticipant;
	
	@Column(nullable = false)
	private Boolean results;
	
	public Event() {
		
	}

	public Event(String name, String description, String address, LocalDateTime startDate, LocalDateTime endDate,
			String type, Integer minParticipants, Integer maxParticipants, UUID creatorId, Double price,
			String category, String gender, String typeParticipant, Boolean results) {
		this.name = name;
		this.description = description;
		this.address = address;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.minParticipants = minParticipants;
		this.maxParticipants = maxParticipants;
		this.creatorId = creatorId;
		this.price = price;
		this.category = category;
		this.gender = gender;
		this.typeParticipant = typeParticipant;
		this.results = results;
	}
	
	public Event (EventRegisterDTO event, UUID creatorId) {
		this.name = event.getName();
		this.description = event.getDescription();
		this.address = event.getAddress();
		this.startDate = event.getStartDate();
		this.endDate = event.getEndDate();
		this.type = event.getType();
		this.minParticipants = event.getMinParticipants();
		this.maxParticipants = event.getMaxParticipants();
		this.creatorId = creatorId;
		this.price = event.getPrice();
		this.category = event.getCategory();
		this.gender = event.getGender();
		this.typeParticipant = event.getTypeParticipant();
		this.results = event.getResults();
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

	public UUID getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(UUID creatorId) {
		this.creatorId = creatorId;
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

	public UUID getId() {
		return id;
	}
	
}
