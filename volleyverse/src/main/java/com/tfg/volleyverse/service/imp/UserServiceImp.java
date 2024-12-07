package com.tfg.volleyverse.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterUserDTO;
import com.tfg.volleyverse.dto.UpdateUserDTO;
import com.tfg.volleyverse.dto.UserDTO;
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
		if (loginUser(new LoginDTO(register.getEmail(), register.getPassword(), "user")) != null) {
			return false;
		}
		userRepository.save(new User(register));
		return true;
	}

	@Override
	public LoginDTO loginUser(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			if (!login.getType().equals("user")) {
				login.setType("user");
			}
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
			if (this.userRepository.save(user) != null) {
				return new LoginDTO(user.getEmail(), user.getPassword(), "user");
			}
		} 
		return null;
	}

	@Override
	public boolean deleteUser(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			this.userRepository.delete(user);
			return true;
		} 
		return false;
	}

	@Override
	public UserDTO getUser(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			return new UserDTO(user);
		}
		return null;
	}

}
