package com.tfg.volleyverse.service.imp;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.StatsDTO;
import com.tfg.volleyverse.model.Stats;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.StatsRepository;
import com.tfg.volleyverse.repository.UserRepository;
import com.tfg.volleyverse.service.StatsService;

@Service
public class StatsServiceImp implements StatsService {

	@Autowired
	private StatsRepository statsRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean updateStats(StatsDTO stats) {
		User user = this.userRepository.findByEmailAndPassword(stats.getLogin().getEmail(), stats.getLogin().getPassword());
		if (user != null && user.getType().equals("player")) {
			Stats statsSaved = this.statsRepository.save(new Stats(stats, user.getIde()));
			if (statsSaved != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public StatsDTO getStats(UUID playerId) {
		Stats stats = this.statsRepository.findByPlayerId(playerId);
		if (stats != null) {
			return new StatsDTO(stats);
		}
		return null;
	}

}
