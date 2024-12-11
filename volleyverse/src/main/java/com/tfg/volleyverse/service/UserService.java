package com.tfg.volleyverse.service;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterUserDTO;

public interface UserService {

	LoginDTO login (LoginDTO login);
	
	boolean delete (LoginDTO login);
	
	Object getUserData (LoginDTO login);
	
	boolean updateProfileImg (String email, String fileName);
}
