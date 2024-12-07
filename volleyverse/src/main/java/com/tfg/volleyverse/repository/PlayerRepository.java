package com.tfg.volleyverse.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository <Player, UUID> {

	Optional<Player> findById(UUID id);
	
	Player save(Player user);
	
	void delete(Player user);
	
}
