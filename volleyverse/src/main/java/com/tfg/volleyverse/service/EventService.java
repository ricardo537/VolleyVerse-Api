package com.tfg.volleyverse.service;

import java.util.List;
import java.util.UUID;

import com.tfg.volleyverse.dto.DeleteEventDTO;
import com.tfg.volleyverse.dto.EventDTO;
import com.tfg.volleyverse.dto.EventRegisterDTO;
import com.tfg.volleyverse.dto.FilterEventDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.MyEventDTO;
import com.tfg.volleyverse.model.Event;

public interface EventService {
	
	boolean addEvent (EventRegisterDTO event);
	
	boolean deleteEvent (DeleteEventDTO event);
	
	List<Event> getEventsToSetResults ();
	
	List<Event> getEventsToParticipate (String category, String type, String gender);
	
	List<MyEventDTO> getMyEventsToRun (LoginDTO login);
	
	List<MyEventDTO> getMyCompletedEvents (LoginDTO login);
	
	List<EventDTO> getFilteredEvents (FilterEventDTO filter, int page, int size);
	
}
