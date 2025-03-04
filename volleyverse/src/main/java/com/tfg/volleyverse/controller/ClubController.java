package com.tfg.volleyverse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterClubDTO;
import com.tfg.volleyverse.dto.UpdateClubDTO;

public interface ClubController {

	@PostMapping("/register")
	public ResponseEntity<Boolean> registerClub (@RequestBody RegisterClubDTO register);
	
	@PostMapping("/update")
	public ResponseEntity<LoginDTO> updateClub (@RequestBody UpdateClubDTO update);
	
}
