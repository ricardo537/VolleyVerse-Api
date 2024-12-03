package com.tfg.volleyverse.service.imp;

import org.springframework.beans.factory.annotation.Autowired;

import com.tfg.volleyverse.dto.RegisterClubDTO;
import com.tfg.volleyverse.model.Club;
import com.tfg.volleyverse.repository.ClubRepository;
import com.tfg.volleyverse.service.ClubService;

public class ClubServiceImp implements ClubService {
	
	@Autowired
	private ClubRepository clubRepository;

	@Override
	public boolean registerClub(RegisterClubDTO register) {
		//Hacer excepcion por si la contraseña o el email no son válidos
		if (existClub(register)) {
			return false;
		}
		Club club = clubRepository.save(new Club(register));
		return (club != null);
	}

	@Override
	public boolean existClub(RegisterClubDTO register) {
		Club club = clubRepository.findByEmailAndPassword(register.getEmail(), register.getPassword());
		return (club != null);
	}

}
