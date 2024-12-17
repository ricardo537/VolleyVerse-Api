package com.tfg.volleyverse.service;

import java.util.List;
import java.util.UUID;

import com.tfg.volleyverse.dto.PlayerResumeDTO;
import com.tfg.volleyverse.dto.TeamCreationDTO;
import com.tfg.volleyverse.model.Player;

public interface TeamService {

	public UUID createTeam(TeamCreationDTO team);
	
	public List<PlayerResumeDTO> getMembersOfTeam (UUID teamId);
	
}
