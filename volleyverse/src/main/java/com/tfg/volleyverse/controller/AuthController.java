package com.tfg.volleyverse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.service.imp.UserServiceImp;

@RestController
@RequestMapping("volleyverse/api/v1/auth")
public class AuthController {
	
	@Autowired
	private UserServiceImp userService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginDTO> login (@RequestBody LoginDTO login) {
		LoginDTO success = this.userService.login(login);
		if (success != null) {
			return new ResponseEntity<LoginDTO>(success, HttpStatus.OK);
		} 
		return new ResponseEntity<LoginDTO>(success, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete (@RequestBody LoginDTO login) {
		boolean success = this.userService.delete(login);
		if (success) {
			return new ResponseEntity<Boolean>(success, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(success, HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/getProfile")
	public ResponseEntity<Object> getProfile (@RequestBody LoginDTO login) {
		Object success = this.userService.getUserData(login);
		if (success != null) {
			return new ResponseEntity<Object>(success, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(success, HttpStatus.NOT_FOUND);
	}
}
