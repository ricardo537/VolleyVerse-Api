package com.tfg.volleyverse.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterClubDTO;
import com.tfg.volleyverse.model.Club;
import com.tfg.volleyverse.repository.ClubRepository;
import com.tfg.volleyverse.service.ClubService;

@Service
public class ClubServiceImp implements ClubService {
	
	@Autowired
	private ClubRepository clubRepository;

	@Override
	public boolean registerClub(RegisterClubDTO register) {
		//Hacer excepcion por si la contraseña o el email no son válidos
		if (loginClub(new LoginDTO(register.getEmail(), register.getPassword())) != null) {
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

}
