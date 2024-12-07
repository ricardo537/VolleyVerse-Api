package com.tfg.volleyverse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.volleyverse.dto.ClubDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterClubDTO;
import com.tfg.volleyverse.service.imp.ClubServiceImp;
import com.tfg.volleyverse.service.imp.PlayerServiceImp;

@RestController
@RequestMapping("volleyverse/api/v1/auth")
public class AuthController {
	
	@Autowired
	private ClubServiceImp clubService;
	
	@Autowired
	private PlayerServiceImp userService;
	
	//Falta cambiarlo para que solo se pueda guardar un email por perfil sea de usuario o de club
	@PostMapping("/login")
	public ResponseEntity<LoginDTO> login (@RequestBody LoginDTO login) {
		return new ResponseEntity<LoginDTO>(login, HttpStatus.OK);
	}
	
	//Falta cambiarlo para que filtre según el tipo
	/*
	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete (@RequestBody LoginDTO login) {
		LoginDTO success = this.clubService.loginClub(login);
		Boolean response = false;
		if (success != null) {
			response = this.clubService.deleteClub(login);
			if (response) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} else {
			success = this.userService.loginUser(login);
			if (success != null) {
				response = this.userService.deleteUser(login);
				if (response) {
					return new ResponseEntity<>(response, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);	
			}
		}
	}
	
	@PostMapping("/getProfile")
	public ResponseEntity<Object> getProfile (@RequestBody LoginDTO login) {
		Object success = null;
		switch (login.getType()) {
		case ("club"): {
			success = this.clubService.getClub(login);
			if (success != null) {
				return new ResponseEntity<>(success, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(success, HttpStatus.NOT_FOUND);
			}
		}
		case ("user"): {
			success = this.userService.getUser(login);
			if (success != null) {
				return new ResponseEntity<>(success, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(success, HttpStatus.NOT_FOUND);
			}
		}
		default: {
			return new ResponseEntity<>(success, HttpStatus.NOT_FOUND);
		}
		}
	}*/
}
