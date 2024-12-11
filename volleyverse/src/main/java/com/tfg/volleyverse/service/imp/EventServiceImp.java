package com.tfg.volleyverse.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.EventRegisterDTO;
import com.tfg.volleyverse.model.Event;
import com.tfg.volleyverse.repository.EventRepository;
import com.tfg.volleyverse.service.EventService;

@Service
public class EventServiceImp implements EventService {
	
	@Autowired
	private EventRepository eventRepository;

	@Override
	public boolean addEvent(EventRegisterDTO eventDTO) {
		Event event = this.eventRepository.save(new Event(eventDTO));
		if (event != null) {
			return true;
		} 
		return false;
	}

	@Override
	public boolean deleteEvent(UUID id_event) {
		Optional<Event> event = this.eventRepository.findById(id_event);
		if (event.isPresent()) {
			this.eventRepository.delete(event.get());
			return true;
		}
		return false;
	}

	@Override
	public List<Event> getAll() {
		return this.eventRepository.findAll();
	}
	
}
