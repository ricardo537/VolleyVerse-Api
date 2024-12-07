package com.tfg.volleyverse.service;

import com.tfg.volleyverse.dto.ClubDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterClubDTO;
import com.tfg.volleyverse.dto.UpdateClubDTO;

public interface ClubService {

	boolean registerClub (RegisterClubDTO register);
	
	LoginDTO updateClub (UpdateClubDTO update);
}
