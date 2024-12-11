package com.tfg.volleyverse.model;

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
	private String place;
	
	@Column(nullable = false)
	private String start_date;
	
	@Column(nullable = false)
	private String end_date;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private Integer min_participants;
	
	@Column(nullable = false)
	private Integer max_participants;
	
	@Column(nullable = false)
	private String creator_email;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = true)
	private String court_img;
	
	@Column(nullable = false)
	private String type_of_public;
	
	public Event() {
		
	}
	
	public Event(String name, String description, String place, String start_date, String end_date, String type,
			Integer min_participants, Integer max_participants, String creator_email, Double price, String court_img, String type_of_public) {
		this.type_of_public = type_of_public;
		this.name = name;
		this.description = description;
		this.place = place;
		this.start_date = start_date;
		this.end_date = end_date;
		this.type = type;
		this.min_participants = min_participants;
		this.max_participants = max_participants;
		this.creator_email = creator_email;
		this.price = price;
		this.court_img = court_img;
	}

	public Event(EventRegisterDTO event) {
		this.type_of_public = event.getType_of_public();
		this.place = event.getPlace();
		this.name = event.getName();
		this.description = event.getDescription();
		this.start_date = event.getStart_date();
		this.end_date = event.getEnd_date();
		this.type = event.getType();
		this.min_participants = event.getMin_participants();
		this.max_participants = event.getMax_participants();
		this.creator_email = event.getCreator_email();
		this.price = event.getPrice();
		this.court_img = event.getCourt_img();
	}

	public String getType_of_public() {
		return type_of_public;
	}

	public void setType_of_public(String type_of_public) {
		this.type_of_public = type_of_public;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getMin_participants() {
		return min_participants;
	}

	public void setMin_participants(Integer min_participants) {
		this.min_participants = min_participants;
	}

	public Integer getMax_participants() {
		return max_participants;
	}

	public void setMax_participants(Integer max_participants) {
		this.max_participants = max_participants;
	}

	public String getCreator_email() {
		return creator_email;
	}

	public void setCreator_email(String creator_email) {
		this.creator_email = creator_email;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCourt_img() {
		return court_img;
	}

	public void setCourt_img(String court_img) {
		this.court_img = court_img;
	}

	public UUID getId() {
		return id;
	}
	
}
