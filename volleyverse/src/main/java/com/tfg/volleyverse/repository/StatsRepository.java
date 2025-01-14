package com.tfg.volleyverse.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.Stats;

@Repository
public interface StatsRepository extends JpaRepository<Stats, UUID>{

	Stats save (Stats stats);
	
	Stats findByPlayerId(UUID playerId);
}
