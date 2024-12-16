package com.tfg.volleyverse.service.imp;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.model.Play;
import com.tfg.volleyverse.model.PlayId;
import com.tfg.volleyverse.repository.PlayRepository;
import com.tfg.volleyverse.service.PlayService;

@Service
public class PlayServiceImp implements PlayService {
	
	@Autowired
	private PlayRepository playRepository;

	@Override
	public boolean addPlayer(Play play) {
		Play success = this.playRepository.save(play);
		if (success != null) {
			return true;
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
