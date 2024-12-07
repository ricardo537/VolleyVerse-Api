package com.tfg.volleyverse.service;

import com.tfg.volleyverse.dto.ClubDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterClubDTO;
import com.tfg.volleyverse.dto.UpdateClubDTO;

public interface ClubService {

	boolean registerClub (RegisterClubDTO register);
	
	LoginDTO loginClub (LoginDTO login);
	
	LoginDTO updateClub (UpdateClubDTO update);
	
	boolean deleteClub (LoginDTO login);
	
	ClubDTO getClub (LoginDTO login);
}
