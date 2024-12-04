package com.tfg.volleyverse.service;

import com.tfg.volleyverse.dto.RegisterUserDTO;
import com.tfg.volleyverse.model.User;

public interface UserService {

	boolean registerUser (RegisterUserDTO register);
	
	boolean existUser (String email, String password);
	
}
