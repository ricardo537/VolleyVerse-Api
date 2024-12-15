package com.tfg.volleyverse.service.imp;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.TeamCreationDTO;
import com.tfg.volleyverse.model.Team;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.TeamRepository;
import com.tfg.volleyverse.repository.UserRepository;
import com.tfg.volleyverse.service.TeamService;

@Service
public class TeamServiceImp implements TeamService {

	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UUID createTeam(TeamCreationDTO team) {
		UUID club_id = this.getClubId(team.getLogin());
		team.setClub_id(club_id);
		Team teamSaved = this.teamRepository.save(new Team(team));
		if (teamSaved != null) {
			return teamSaved.getId();
		}
		return null;
	}
	
	private UUID getClubId(LoginDTO login) {
		if (login.getType().equals("club")) {
			User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
			if (user != null) {
				return user.getIde();
			}
		}
		return null;
	}

}
