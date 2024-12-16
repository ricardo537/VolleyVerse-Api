package com.tfg.volleyverse.service;

import java.util.UUID;

import com.tfg.volleyverse.model.Play;

public interface PlayService {

	public boolean addPlayer(Play play);
	
	public boolean deletePlayer(UUID teamId, UUID playerId);
	
}
