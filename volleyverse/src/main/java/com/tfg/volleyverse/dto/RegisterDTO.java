package com.tfg.volleyverse.dto;

public class RegisterDTO {

	private String email;
	private String password;
	private String name;
	private String last_name;
	
	RegisterDTO () {
		
	}
	
	RegisterDTO (String email, String password, String password_confirm, String name, String last_name) {
		//Falta añadir que lance una exceptción en caso de que la contraseña y la confirmación no sean la misma
		if (password.equals(password_confirm)) {
			this.email = email;
			this.password = password;
			this.name = name;
			this.last_name = last_name;
		}
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
	
}
