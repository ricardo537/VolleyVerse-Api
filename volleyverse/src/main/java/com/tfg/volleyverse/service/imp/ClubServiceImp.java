package com.tfg.volleyverse.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.ClubDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterClubDTO;
import com.tfg.volleyverse.dto.RegisterUserDTO;
import com.tfg.volleyverse.dto.UpdateClubDTO;
import com.tfg.volleyverse.model.Club;
import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.ClubRepository;
import com.tfg.volleyverse.repository.UserRepository;
import com.tfg.volleyverse.service.ClubService;

@Service
public class ClubServiceImp implements ClubService {
	
	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean registerClub(RegisterClubDTO register) {
		Club club = this.clubRepository.save(new Club(register));
		if (club != null) {
			User user = this.userRepository.save(new User(new RegisterUserDTO(register.getEmail(), register.getPassword(), "club", club.getId())));
			if (user != null) {
				return true;
			} else {
				this.clubRepository.delete(club);
			}
		}
		return false;
	}

	@Override
	public LoginDTO updateClub(UpdateClubDTO update) {
		User user = this.userRepository.findByEmailAndPassword(update.getLogin().getEmail(), update.getLogin().getPassword());
		if (user != null) {
			if (!update.getEmail().equals(update.getLogin().getEmail())) {
				User userExist = this.userRepository.findByEmail(update.getEmail());
				if (userExist == null) {
					this.userRepository.delete(user);
					user.setEmail(update.getEmail());
					user.setPassword(update.getPassword());
					Optional<Club> clubOp = this.clubRepository.findById(user.getId_user());
					if (clubOp.isPresent()) {
						user = this.userRepository.save(user);
						Club club = clubOp.get();
						club.update(update);
						this.clubRepository.save(club);
						return new LoginDTO(user);
					}
				}
			} else {
				user.setPassword(update.getPassword());
				Optional<Club> clubOp = this.clubRepository.findById(user.getId_user());
				if (clubOp.isPresent()) {
					user = this.userRepository.save(user);
					Club club = clubOp.get();
					club.update(update);
					this.clubRepository.save(club);
					return new LoginDTO(user);
				}
			}
		}
		return null;
	}

}
