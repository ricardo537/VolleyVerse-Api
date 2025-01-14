package com.tfg.volleyverse.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tfg.volleyverse.model.Event;

public class EventJoinedDTO {
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
	private String creator;
	private UUID participantId;
	private int num_participants;
	
	public EventJoinedDTO () {
		
	}

	public EventJoinedDTO(UUID id, String name, String description, String address, LocalDateTime startDate,
			LocalDateTime endDate, String type, Integer minParticipants, Integer maxParticipants, Double price,
			String category, String gender, String typeParticipant, Boolean results, String creator, UUID participantId, int num_participants) {
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
		this.creator = creator;
		this.participantId = participantId;
		this.num_participants = num_participants;
	}
	
	public EventJoinedDTO (Event event, String creator, UUID participantId, int num_participants) {
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
		this.creator = creator;
		this.participantId = participantId;
		this.num_participants = num_participants;
	}
	
	public boolean isValid (FilterEventDTO filter) {
		if (!filter.getCategory().equals("")) {
			if (!filter.getCategory().equals(this.category)) {
				return false;
			}
		}
		if (!filter.getGender().equals("")) {
			if (!filter.getGender().equals(this.gender)) {
				return false;
			}
		}
		if (!filter.getType().equals("")) {
			if (!filter.getType().equals(this.type)) {
				return false;
			}
		}
		if (!filter.getTypeParticipant().equals("")) {
			if (!filter.getTypeParticipant().equals(this.typeParticipant)) {
				return false;
			}
		}
		return true;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public UUID getParticipantId() {
		return participantId;
	}

	public void setParticipantId(UUID participantId) {
		this.participantId = participantId;
	}

	public int getNum_participants() {
		return num_participants;
	}

	public void setNum_participants(int num_participants) {
		this.num_participants = num_participants;
	}
	
}
