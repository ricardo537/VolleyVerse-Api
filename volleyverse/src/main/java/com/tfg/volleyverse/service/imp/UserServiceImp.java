package com.tfg.volleyverse.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterUserDTO;
import com.tfg.volleyverse.dto.UpdateUserDTO;
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
		if (loginUser(new LoginDTO(register.getEmail(), register.getPassword())) != null) {
			return false;
		}
		userRepository.save(new User(register));
		return true;
	}

	@Override
	public LoginDTO loginUser(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			return login;
		} else {
			return null;
		}
	}

	@Override
	public LoginDTO updateUser(UpdateUserDTO update) {
		User user = this.userRepository.findByEmailAndPassword(update.getLogin().getEmail(), update.getLogin().getPassword());
		if (user != null) {
			user.update(update);
			this.userRepository.save(user);
			return new LoginDTO(user.getEmail(), user.getPassword());
		} else {
			return null;
		}
	}

}
