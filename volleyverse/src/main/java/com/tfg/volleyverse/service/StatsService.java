package com.tfg.volleyverse.service;

import java.util.UUID;

import com.tfg.volleyverse.dto.StatsDTO;

public interface StatsService {

	boolean updateStats (StatsDTO stats);
	
	StatsDTO getStats (UUID playerId);
}
