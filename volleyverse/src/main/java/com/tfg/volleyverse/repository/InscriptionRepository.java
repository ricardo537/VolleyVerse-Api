package com.tfg.volleyverse.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.Inscription;
import com.tfg.volleyverse.model.InscriptionId;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, InscriptionId> {
	
	Inscription save (Inscription inscription);
	
	void delete (Inscription inscription);
	
	Optional<Inscription> findByParticipantIdAndEventId (UUID participantId, UUID eventId);
	
	List<Inscription> findByEventId (UUID eventId);
	
	List<Inscription> findByParticipantId (UUID participantId);
}
