package com.tfg.volleyverse.service.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.EventRegisterDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.model.Event;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.EventRepository;
import com.tfg.volleyverse.repository.UserRepository;
import com.tfg.volleyverse.service.EventService;

@Service
public class EventServiceImp implements EventService {
	
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean addEvent(EventRegisterDTO eventDTO) {
		LocalDateTime now = LocalDateTime.now();
		if (eventDTO.getStartDate().isAfter(now) && eventDTO.getEndDate().isAfter(eventDTO.getStartDate())) {
			User user = this.userRepository.findByEmailAndPassword(eventDTO.getLogin().getEmail(), eventDTO.getLogin().getPassword());
			if (user != null) {
				Event event = this.eventRepository.save(new Event(eventDTO, user.getIde()));
				if (event != null) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean deleteEvent(UUID id_event) {
		//Falta borrar las trazas
		Optional<Event> event = this.eventRepository.findById(id_event);
		if (event.isPresent()) {
			this.eventRepository.delete(event.get());
			return true;
		}
		return false;
	}

	@Override
	public List<Event> getEventsToSetResults() {
		return this.eventRepository.findByStartDateAfter(LocalDateTime.now());
	}

	@Override
	public List<Event> getEventsToParticipate(String category, String type, String gender) {
		
		return null;
	}

	@Override
	public List<Event> getMyEvents(LoginDTO login) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
