package com.tfg.volleyverse.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.volleyverse.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
	
	// Buscar evento por ID
    Optional<Event> findById(UUID id);

    // Obtener todos los eventos
    List<Event> findAll();

    // Buscar eventos con fecha de inicio que no han empezado
    List<Event> findByStartDateAfter(LocalDateTime now);

    // Buscar eventos por categoría y fecha de inicio que no han empezado
    List<Event> findByCategoryAndStartDateAfter(String category, LocalDateTime now);

    // Buscar eventos por género y fecha de inicio que no han empezado
    List<Event> findByGenderAndStartDateAfter(String gender, LocalDateTime now);

    // Buscar eventos por tipo y fecha de inicio que no han empezado
    List<Event> findByTypeAndStartDateAfter(String type, LocalDateTime now);

    // Buscar eventos con resultados confirmados y fecha final ya pasada
    List<Event> findByResultsTrueAndEndDateBefore(LocalDateTime now);

    // Buscar eventos por categoría, tipo y fecha de inicio que no han empezado
    List<Event> findByCategoryAndTypeAndStartDateAfter(String category, String type, LocalDateTime now);

    // Buscar eventos por categoría, género y fecha de inicio que no han empezado
    List<Event> findByCategoryAndGenderAndStartDateAfter(String category, String gender, LocalDateTime now);

    // Buscar eventos por tipo, género y fecha de inicio que no han empezado
    List<Event> findByTypeAndGenderAndStartDateAfter(String type, String gender, LocalDateTime now);

    //Obtener todos los eventos que se han organizado
    List<Event> findByCreatorId(UUID creatorId);
    
    // Guardar evento
    Event save(Event event);

    // Eliminar evento
    void delete(Event event);
    
}
