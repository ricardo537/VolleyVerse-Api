package com.tfg.volleyverse.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.UserRepository;
import com.tfg.volleyverse.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public LoginDTO login(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			return login;
		}
		return null;
	}

	@Override
	public boolean delete(LoginDTO login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getUserData(LoginDTO login) {
		// TODO Auto-generated method stub
		return null;
	}

}
