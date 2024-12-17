package com.tfg.volleyverse.service;

import java.util.List;
import java.util.UUID;

import com.tfg.volleyverse.dto.AddPlayerToTeamDTO;
import com.tfg.volleyverse.model.Play;
import com.tfg.volleyverse.model.Player;

public interface PlayService {

	public boolean addPlayer(Play play);
	
	public boolean deletePlayer(UUID teamId, UUID playerId);
	
}
