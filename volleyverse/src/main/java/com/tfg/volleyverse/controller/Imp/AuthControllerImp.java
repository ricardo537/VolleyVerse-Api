package com.tfg.volleyverse.controller.Imp;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.volleyverse.controller.AuthController;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.service.AuthService;
import com.tfg.volleyverse.service.imp.UserServiceImp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("volleyverse/api/v1/auth")
public class AuthControllerImp implements AuthController {
	
	@Autowired
	private UserServiceImp userService;
	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthService authService;
	
	public AuthControllerImp(AuthenticationManager authenticationManager, AuthService authService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.userDetailsService = userDetailsService;
    }
	
	@Override
	public ResponseEntity<String> login (@RequestBody LoginDTO login) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		LoginDTO success = this.userService.login(login);
		if (success != null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(login.getEmail());
			return new ResponseEntity<String>(authService.generateToken(userDetails), HttpStatus.OK);
		} 
		return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<Boolean> delete (@RequestBody LoginDTO login) {
		boolean success = this.userService.delete(login);
		if (success) {
			return new ResponseEntity<Boolean>(success, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(success, HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	public ResponseEntity<Object> getProfile (@RequestBody LoginDTO login) {
		Object success = this.userService.getUserData(login);
		if (success != null) {
			return new ResponseEntity<Object>(success, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(success, HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<String> getMyId (@RequestBody LoginDTO login) {
		UUID result = this.userService.getMyId(login);
		if (result != null) {
			return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		}
		return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}
}
