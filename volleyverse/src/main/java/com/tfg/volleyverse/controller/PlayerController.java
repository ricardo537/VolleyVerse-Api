package com.tfg.volleyverse.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.PlayerResumeDTO;
import com.tfg.volleyverse.dto.RegisterPlayerDTO;
import com.tfg.volleyverse.dto.UpdatePlayerDTO;

public interface PlayerController {

	@PostMapping("/register")
	public ResponseEntity<Boolean> registerUser (@RequestBody RegisterPlayerDTO register);
	
	@PostMapping("/update")
	public ResponseEntity<LoginDTO> updateUser (@RequestBody UpdatePlayerDTO update);
	
	@GetMapping("/search") 
	public ResponseEntity<List<PlayerResumeDTO>> searchPlayers (@RequestParam String gender, @RequestParam String name);
	
}
