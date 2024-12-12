package com.tfg.volleyverse.model;

import java.util.UUID;

import com.tfg.volleyverse.dto.RegisterUserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false, unique = true)
	private UUID ide;
	
	@Column(nullable = true)
	private String img;
	
	private User () {
		
	}

	public User(String email, String password, String type, UUID ide, String img) {
		this.email = email;
		this.password = password;
		this.type = type;
		this.ide = ide;
		this.img = img;
	}
	
	public User(RegisterUserDTO register) {
		this.email = register.getEmail();
		this.password = register.getPassword();
		this.type = register.getType();
		this.ide = register.getId_user();
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UUID getIde() {
		return ide;
	}

	public void setIde(UUID ide) {
		this.ide = ide;
	}
	
}
