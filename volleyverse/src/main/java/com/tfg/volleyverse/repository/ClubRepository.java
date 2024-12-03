package com.tfg.volleyverse.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, UUID> {

	Club findByEmailAndPassword(String email, String password);
	
	Club save(Club club);
}
