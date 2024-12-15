package com.tfg.volleyverse.service;

import java.util.UUID;

import com.tfg.volleyverse.dto.TeamCreationDTO;

public interface TeamService {

	public UUID createTeam(TeamCreationDTO team);
	
}
