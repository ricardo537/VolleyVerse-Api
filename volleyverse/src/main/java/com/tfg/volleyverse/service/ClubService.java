package com.tfg.volleyverse.service;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterClubDTO;

public interface ClubService {

	boolean registerClub (RegisterClubDTO register);
	
	LoginDTO loginClub (LoginDTO login);
	
}
