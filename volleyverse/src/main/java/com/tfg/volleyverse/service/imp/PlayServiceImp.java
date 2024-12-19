package com.tfg.volleyverse.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.AddPlayerToTeamDTO;
import com.tfg.volleyverse.model.Play;
import com.tfg.volleyverse.model.PlayId;
import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.model.Team;
import com.tfg.volleyverse.repository.PlayRepository;
import com.tfg.volleyverse.repository.TeamRepository;
import com.tfg.volleyverse.service.PlayService;

@Service
public class PlayServiceImp implements PlayService {
	
	@Autowired
	private PlayRepository playRepository;
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public boolean addPlayer(Play play) {
		Optional<Play> playExists = this.playRepository.findById(new PlayId(play));
		if (playExists.isEmpty()) {
			Team team = this.teamRepository.getById(play.getTeamId());
			if (team != null) {
				List<Play> members = this.playRepository.findByTeamId(team.getId());
				switch (team.getType()) {
				case ("2x2") : {
					if (members.size() < 0 || members.size() >= 4) {
						return false;
					}
					break;
				}
				case ("4x4") : {
					if (members.size() < 0 || members.size() >= 6) {
						return false;
					}
					break;
				}
				case ("6x6") : {
					if (members.size() < 0 || members.size() >= 21) {
						return false;
					}
					break;
				}
				}
				Play success = this.playRepository.save(play);
				if (success != null) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean deletePlayer(UUID teamId, UUID playerId) {
		Optional<Play> success = this.playRepository.findById(new PlayId(teamId, playerId));
		if (success.isPresent()) {
			this.playRepository.deleteById(new PlayId(teamId, playerId));
			return true;
		}
		return false;
	}

}
