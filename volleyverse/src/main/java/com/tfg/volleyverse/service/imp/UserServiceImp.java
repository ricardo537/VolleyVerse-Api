package com.tfg.volleyverse.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.RegisterUserDTO;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.UserRepository;
import com.tfg.volleyverse.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean registerUser(RegisterUserDTO register) {
		//Falta añadir un try catch en caso de que el email no sea valido
		if (existUser(register.getEmail(), register.getPassword())) {
			return false;
		}
		userRepository.save(new User(register));
		return true;
	}

	@Override
	public boolean existUser(String email, String password) {
		User user = this.userRepository.findByEmailAndPassword(email, password);
		return (user != null);
	}

}
