package com.tfg.volleyverse.service;

import com.tfg.volleyverse.dto.RegisterClubDTO;

public interface ClubService {

	boolean registerClub (RegisterClubDTO register);
	
	boolean existClub (RegisterClubDTO register);
	
}
