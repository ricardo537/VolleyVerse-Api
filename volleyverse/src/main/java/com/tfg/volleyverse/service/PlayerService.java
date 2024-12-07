package com.tfg.volleyverse.service;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterPlayerDTO;
import com.tfg.volleyverse.dto.UpdatePlayerDTO;
import com.tfg.volleyverse.dto.PlayerDTO;
import com.tfg.volleyverse.model.Player;

public interface PlayerService {

	boolean registerPlayer (RegisterPlayerDTO register);
	
	LoginDTO updatePlayer (UpdatePlayerDTO update);
	
}
