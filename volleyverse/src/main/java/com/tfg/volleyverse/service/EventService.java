package com.tfg.volleyverse.service;

import java.util.List;
import java.util.UUID;

import com.tfg.volleyverse.dto.EventRegisterDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.model.Event;

public interface EventService {
	
	boolean addEvent (EventRegisterDTO event);
	
	boolean deleteEvent (UUID id_event);
	
	List<Event> getEventsToSetResults ();
	
	List<Event> getEventsToParticipate (String category, String type, String gender);
	
	List<Event> getMyEvents (LoginDTO login);
}
