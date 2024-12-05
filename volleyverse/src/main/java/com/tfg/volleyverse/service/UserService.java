package com.tfg.volleyverse.service;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterUserDTO;
import com.tfg.volleyverse.dto.UpdateUserDTO;
import com.tfg.volleyverse.model.User;

public interface UserService {

	boolean registerUser (RegisterUserDTO register);
	
	LoginDTO loginUser (LoginDTO login);
	
	LoginDTO updateUser (UpdateUserDTO update);
	
}
