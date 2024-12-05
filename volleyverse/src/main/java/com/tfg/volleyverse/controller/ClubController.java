package com.tfg.volleyverse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterClubDTO;
import com.tfg.volleyverse.dto.UpdateClubDTO;
import com.tfg.volleyverse.service.imp.ClubServiceImp;

@RestController
@RequestMapping("volleyverse/api/v1/clubs")
public class ClubController {
	
	@Autowired
	private ClubServiceImp clubService;
	
	@PostMapping("/register")
	public ResponseEntity<Boolean> registerClub (@RequestBody RegisterClubDTO register) {
		Boolean success = this.clubService.registerClub(register);
		if (success) {
			return new ResponseEntity<>(success, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<LoginDTO> updateClub (@RequestBody UpdateClubDTO update) {
		LoginDTO success = this.clubService.updateClub(update);
		if (success != null) {
			return new ResponseEntity<>(success, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
		}
	}
}
