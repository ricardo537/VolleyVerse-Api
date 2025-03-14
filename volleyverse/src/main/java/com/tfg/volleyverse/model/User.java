package com.tfg.volleyverse.model;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tfg.volleyverse.dto.RegisterUserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User implements UserDetails{

	@Id
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	@Column(nullable = false, unique = true)
	private UUID ide;
	
	@Column(nullable = true)
	private String img;
	
	private User () {
		
	}

	public User(String email, String password, String type, UUID ide, String img) {
		this.email = email;
		this.password = password;
		this.roles = List.of(type);
		this.ide = ide;
		this.img = img;
	}
	
	public User(RegisterUserDTO register) {
		this.email = register.getEmail();
		this.password = register.getPassword();
		this.roles = List.of(register.getType());
		this.ide = register.getId_user();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public UUID getIde() {
		return ide;
	}

	public void setIde(UUID ide) {
		this.ide = ide;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	
}
