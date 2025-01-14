package com.tfg.volleyverse.service;

import java.util.List;

import com.tfg.volleyverse.dto.ClubDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterClubDTO;
import com.tfg.volleyverse.dto.UpdateClubDTO;

public interface ClubService {

	boolean registerClub (RegisterClubDTO register);
	
	LoginDTO updateClub (UpdateClubDTO update);
	
	List<ClubDTO> searchClubs (String zip_code);
}
