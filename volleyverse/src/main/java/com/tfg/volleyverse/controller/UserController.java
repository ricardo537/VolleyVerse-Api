package com.tfg.volleyverse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.volleyverse.dto.RegisterDTO;
import com.tfg.volleyverse.service.imp.UserServiceImp;

@RestController
@RequestMapping("/volleyverse/api/v1/users")
public class UserController {

	@Autowired
	private UserServiceImp userService;
	
	@PostMapping("/register")
	public ResponseEntity<Boolean> registerUser (@RequestBody RegisterDTO register) {
		Boolean success = this.userService.registerUser(register);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}
}
