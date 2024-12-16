package com.tfg.volleyverse.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.ClubDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.PlayerDTO;
import com.tfg.volleyverse.model.Club;
import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.ClubRepository;
import com.tfg.volleyverse.repository.PlayerRepository;
import com.tfg.volleyverse.repository.UserRepository;
import com.tfg.volleyverse.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private ClubRepository clubRepository;

	@Override
	public LoginDTO login(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			return new LoginDTO(user);
		}
		return null;
	}

	@Override
	public boolean delete(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			//Cuando pongo otro case "player" no funciona hay que revisarlo
			switch(login.getType()) {
			case ("club") : {
				Optional<Club> club = this.clubRepository.findById(user.getIde());
				if (club.isEmpty()) {
					return false;
				}
				this.clubRepository.delete(club.get());
				break;
			}
			default : {
				Optional<Player> player = this.playerRepository.findById(user.getIde());
				if (player.isEmpty()) {
					return false;
				}
				this.playerRepository.delete(player.get());
				break;
			}
			}
			this.userRepository.delete(user);
			return true;
		}
		return false;
	}

	@Override
	public Object getUserData(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			switch (login.getType()) {
			case "club": {
				Optional<Club> club = this.clubRepository.findById(user.getIde());
				if (club.isEmpty()) {
					return null;
				}
				return new ClubDTO (club.get());
			}
			default : {
				Optional<Player> player = this.playerRepository.findById(user.getIde());
				if (player.isEmpty()) {
					return null;
				}
				return new PlayerDTO (player.get(), user.getImg());
			}
			}
		}
		return null;
	}

	@Override
	public boolean updateProfileImg(String email, String fileName) {
		User user = this.userRepository.findByEmail(email);
		if (user != null) {
			this.userRepository.delete(user);
			user.setImg(fileName);
			this.userRepository.save(user);
			return true;
		}
		return false;
	}
	
	public User findUser(LoginDTO login) {
		return this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
	}

}
