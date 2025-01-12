package com.tfg.volleyverse.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
	
	// Buscar evento por ID
    Optional<Event> findById(UUID id);
    
    //Obtener eventos paginados
    Page<Event> findByStartDateAfter(LocalDateTime now, Pageable pageable);

    // Buscar eventos con fecha de inicio que no han empezado
    List<Event> findByStartDateAfter(LocalDateTime now);

    // Buscar eventos con resultados confirmados y fecha final ya pasada
    List<Event> findByResultsTrueAndEndDateBefore(LocalDateTime now);

    //Obtener todos los eventos que se han organizado
    List<Event> findByCreatorId(UUID creatorId);
    
    // Guardar evento
    Event save(Event event);

    // Eliminar evento
    void delete(Event event);
    
}
