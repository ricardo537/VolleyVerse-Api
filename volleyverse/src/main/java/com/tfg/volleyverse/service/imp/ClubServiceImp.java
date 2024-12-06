package com.tfg.volleyverse.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterClubDTO;
import com.tfg.volleyverse.dto.UpdateClubDTO;
import com.tfg.volleyverse.model.Club;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.ClubRepository;
import com.tfg.volleyverse.service.ClubService;

@Service
public class ClubServiceImp implements ClubService {
	
	@Autowired
	private ClubRepository clubRepository;

	@Override
	public boolean registerClub(RegisterClubDTO register) {
		//Hacer excepcion por si la contraseña o el email no son válidos
		if (loginClub(new LoginDTO(register.getEmail(), register.getPassword(), "club")) != null) {
			return false;
		}
		Club club = clubRepository.save(new Club(register));
		return (club != null);
	}

	@Override
	public LoginDTO loginClub(LoginDTO login) {
		Club club = clubRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (club != null) {
			return login;
		} else {
			return null;
		}
	}

	@Override
	public LoginDTO updateClub(UpdateClubDTO update) {
		Club club = this.clubRepository.findByEmailAndPassword(update.getLogin().getEmail(), update.getLogin().getPassword());
		if (club != null) {
			club.update(update);
			if (this.clubRepository.save(club) != null) {
				return new LoginDTO(update.getEmail(), update.getPassword(), "club");
			}
		} 
		return null;
	}

	@Override
	public boolean deleteClub(LoginDTO login) {
		Club club = this.clubRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (club != null) {
			this.clubRepository.delete(club);
			return true;
		} 
		return false;
	}

}
