package com.tfg.volleyverse.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
	
	Team save(Team team);
	
	List<Team> findByClubId(UUID clubId);
	
	Optional<Team> findByIdAndClubId(UUID id, UUID clubId);
	
}
