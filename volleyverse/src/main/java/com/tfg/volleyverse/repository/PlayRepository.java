package com.tfg.volleyverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.Play;
import com.tfg.volleyverse.model.PlayId;

@Repository
public interface PlayRepository extends JpaRepository<Play, PlayId>{

	Play save (Play play);
	
}
