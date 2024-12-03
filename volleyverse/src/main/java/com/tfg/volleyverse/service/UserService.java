package com.tfg.volleyverse.service;

import com.tfg.volleyverse.dto.RegisterDTO;

public interface UserService {

	boolean registerUser (RegisterDTO register);
	
	boolean existUser (String email, String password);
	
}
