package com.tfg.volleyverse.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, UUID> {

	Optional<Club> findById(UUID id);
	
	Optional<Club> findByName(String name);
	
	Club save(Club club);
	
	void delete(Club club);
	
	List<Club> findByZipCode(String zipCode);
}
