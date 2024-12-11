package com.tfg.volleyverse.dto;

import java.util.Date;
import java.util.UUID;

public class EventRegisterDTO {
	
	private String place;
	private String name;
	private String description;
	private String start_date;
	private String end_date;
	private String type;
	private Integer min_participants;
	private Integer max_participants;
	private String creator_email;
	private Double price;
	private String court_img;
	private String type_of_public;
	
	public EventRegisterDTO() {
		
	}

	public EventRegisterDTO(String place, String name, String description, String start_date, String end_date, String type,
			Integer min_participants, Integer max_participants, String creator_email, Double price, String court_img, String type_of_public) {
		this.type_of_public = type_of_public;
		this.place = place;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.type = type;
		this.min_participants = min_participants;
		this.max_participants = max_participants;
		this.creator_email = creator_email;
		this.price = price;
		this.court_img = court_img;
	}

	public String getType_of_public() {
		return type_of_public;
	}

	public void setType_of_public(String type_of_public) {
		this.type_of_public = type_of_public;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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

}
