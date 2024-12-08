package com.tfg.volleyverse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterPlayerDTO;
import com.tfg.volleyverse.dto.UpdatePlayerDTO;
import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.service.imp.PlayerServiceImp;
import com.tfg.volleyverse.service.imp.UserServiceImp;

@RestController
@RequestMapping("/volleyverse/api/v1/players")
public class PlayerController {

	@Autowired
	private PlayerServiceImp playerService;
	@Autowired
	private UserServiceImp userService;
	
	@PostMapping("/register")
	public ResponseEntity<Boolean> registerUser (@RequestBody RegisterPlayerDTO register) {
		LoginDTO foundUser = this.userService.login(new LoginDTO(register.getEmail(), register.getPassword(), "player"));
		Boolean success = false;
		if (foundUser == null) {
			success = this.playerService.registerPlayer(register);
			if (success) {
				return new ResponseEntity<>(success, HttpStatus.OK);
			} 
		} 
		return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);

	}
	
	@PostMapping("/update")
	public ResponseEntity<LoginDTO> updateUser (@RequestBody UpdatePlayerDTO update) {
		LoginDTO success = this.playerService.updatePlayer(update);
		if (success != null) {
			return new ResponseEntity<>(success, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
		}
	}
	
}
