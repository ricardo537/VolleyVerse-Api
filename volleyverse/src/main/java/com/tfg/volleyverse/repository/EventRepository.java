package com.tfg.volleyverse.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
	
	Optional<Event> findById(UUID id);
	
	List<Event> findAll();
	
	Event save(Event event);
	
	void delete(Event event);
}
