package com.tfg.volleyverse.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.tfg.volleyverse.dto.LoginDTO;

public interface UserService extends UserDetailsService {

	UserDetails login (LoginDTO login);
	
	boolean delete (LoginDTO login);
	
	Object getUserData (LoginDTO login);
	
	boolean updateProfileImg (String email, String fileName);
	
	UUID getMyId (LoginDTO login);
}
