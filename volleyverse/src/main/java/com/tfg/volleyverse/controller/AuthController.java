package com.tfg.volleyverse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tfg.volleyverse.dto.LoginDTO;

public interface AuthController {
	
	@PostMapping("/login")
	public ResponseEntity<LoginDTO> login (@RequestBody LoginDTO login);
	
	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete (@RequestBody LoginDTO login);
	
	
	@PostMapping("/getProfile")
	public ResponseEntity<Object> getProfile (@RequestBody LoginDTO login);
	
	@PostMapping("/getMyId")
	public ResponseEntity<String> getMyId (@RequestBody LoginDTO login);
}
