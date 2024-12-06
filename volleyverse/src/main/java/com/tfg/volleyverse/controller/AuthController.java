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
import com.tfg.volleyverse.service.imp.ClubServiceImp;
import com.tfg.volleyverse.service.imp.UserServiceImp;

@RestController
@RequestMapping("volleyverse/api/v1/auth")
public class AuthController {
	
	@Autowired
	private ClubServiceImp clubService;
	
	@Autowired
	private UserServiceImp userService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginDTO> login (@RequestBody LoginDTO login) {
		LoginDTO success = null;
		switch (login.getType()) {
			case "club" : {
				success = this.clubService.loginClub(login);
				if (success != null) {
					return new ResponseEntity<>(success, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
				}
			}
			case "user" : {
				success = this.userService.loginUser(login);
				if (success != null) {
					return new ResponseEntity<>(success, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
				}
			}
			default : {
				return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete (@RequestBody LoginDTO login) {
		LoginDTO success = this.clubService.loginClub(login);
		Boolean response = false;
		if (success != null) {
			response = this.clubService.deleteClub(login);
			if (response) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
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
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	
			}
		}
	}
}
