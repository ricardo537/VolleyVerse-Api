package com.tfg.volleyverse.service;

import com.tfg.volleyverse.dto.RegisterUserDTO;

public interface UserService {

	boolean registerUser (RegisterUserDTO register);
	
	boolean existUser (String email, String password);
	
}
