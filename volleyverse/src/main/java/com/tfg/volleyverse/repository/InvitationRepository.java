package com.tfg.volleyverse.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, UUID> {
	
	Invitation save(Invitation invitation);
	
	List<Invitation> findByUserId (UUID userId);
	
	Invitation findByUserIdAndTeamId(UUID userId, UUID teamId);
}
