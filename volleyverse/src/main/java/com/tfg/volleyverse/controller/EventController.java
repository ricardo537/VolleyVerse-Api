package com.tfg.volleyverse.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.net.httpserver.Authenticator.Success;
import com.tfg.volleyverse.dto.DeleteEventDTO;
import com.tfg.volleyverse.dto.EventDTO;
import com.tfg.volleyverse.dto.EventJoinedDTO;
import com.tfg.volleyverse.dto.EventRegisterDTO;
import com.tfg.volleyverse.dto.FilterEventDTO;
import com.tfg.volleyverse.dto.InscriptionDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.MyEventDTO;
import com.tfg.volleyverse.dto.ParticipantsDTO;
import com.tfg.volleyverse.dto.ResumeDTO;
import com.tfg.volleyverse.model.Event;
import com.tfg.volleyverse.service.imp.EventServiceImp;
import com.tfg.volleyverse.service.imp.InscriptionServiceImp;
import com.tfg.volleyverse.service.imp.UserServiceImp;

@RestController
@RequestMapping("/volleyverse/api/v1/events")
public class EventController {
	
	@Autowired
	private EventServiceImp eventService;
	@Autowired
	private UserServiceImp userService;
	@Autowired
	private InscriptionServiceImp inscriptionService;
	
	@PostMapping("/add")
	public ResponseEntity<Boolean> addEvent (@RequestBody EventRegisterDTO event) {
		LoginDTO login = this.userService.login(event.getLogin());
		if (login != null) {
			Boolean success = this.eventService.addEvent(event);
			if (success) {
				return new ResponseEntity<Boolean>(success, HttpStatus.OK);
			}
			return new ResponseEntity<Boolean>(success, HttpStatus.BAD_REQUEST);
		} 
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/getMyEventsToRun")
	public ResponseEntity<List<MyEventDTO>> getMyEventsToRun (@RequestBody LoginDTO login) {
		LoginDTO loginExists = this.userService.login(login);
		if (loginExists != null) {
			List<MyEventDTO> myEvents = this.eventService.getMyEventsToRun(login);
			if (!myEvents.isEmpty()) {
				return new ResponseEntity<List<MyEventDTO>>(myEvents, HttpStatus.OK);
			}
			return new ResponseEntity<List<MyEventDTO>>(myEvents, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MyEventDTO>>(List.of(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/getMyCompletedEvents")
	public ResponseEntity<List<MyEventDTO>> getMyCompletedEvents (@RequestBody LoginDTO login) {
		LoginDTO loginExists = this.userService.login(login);
		if (loginExists != null) {
			List<MyEventDTO> myEvents = this.eventService.getMyCompletedEvents(login);
			if (!myEvents.isEmpty()) {
				return new ResponseEntity<List<MyEventDTO>>(myEvents, HttpStatus.OK);
			}
			return new ResponseEntity<List<MyEventDTO>>(myEvents, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MyEventDTO>>(List.of(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/deleteEvent")
	public ResponseEntity<Boolean> deleteEvent(@RequestBody DeleteEventDTO event) {
		LoginDTO login = this.userService.login(event.getLogin());
		if (login != null) {
			Boolean success = this.eventService.deleteEvent(event);
			if (success) {
				return new ResponseEntity<Boolean>(success, HttpStatus.OK);
			}
			return new ResponseEntity<Boolean>(success, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/getEventsFiltered")
	public ResponseEntity<List<EventDTO>> getFilteredEvents (@RequestBody FilterEventDTO filter, @RequestParam int page, @RequestParam int size) {
		List<EventDTO> events = this.eventService.getFilteredEvents(filter, page, size);
		if (!events.isEmpty()) {
			return new ResponseEntity<List<EventDTO>>(events, HttpStatus.OK);
		}
		return new ResponseEntity<List<EventDTO>>(events, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/join")
	public ResponseEntity<Boolean> joinEvent (@RequestBody InscriptionDTO inscription) {
		LoginDTO loginExists = this.userService.login(inscription.getLogin());
		if (loginExists != null) {
			boolean result = this.inscriptionService.joinEvent(inscription);
			if (result) {
				return new ResponseEntity<Boolean>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(result, HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/unsubscribe")
	public ResponseEntity<Boolean> unsubscribeEvent (@RequestBody InscriptionDTO inscription) {
		LoginDTO loginExists = this.userService.login(inscription.getLogin());
		if (loginExists != null) {
			boolean result = this.inscriptionService.unsubscribeEvent(inscription);
			if (result) {
				return new ResponseEntity<Boolean>(result, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<Boolean>(result, HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/getParticipants")
	public ResponseEntity<List<ResumeDTO>> getParticipants(@RequestBody ParticipantsDTO participantsData) {
		LoginDTO login = this.userService.login(participantsData.getLogin());
		if (login != null) {
			List<ResumeDTO> participants = this.eventService.getParticipants(participantsData);
			if (participants.isEmpty()) {
				return new ResponseEntity<List<ResumeDTO>>(participants, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<ResumeDTO>>(participants, HttpStatus.OK);
		}
		return new ResponseEntity<List<ResumeDTO>>(List.of(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/getPastEventsJoined")
	public ResponseEntity<List<EventJoinedDTO>> getPastEventsJoined(@RequestBody LoginDTO login) {
		LoginDTO loginExists = this.userService.login(login);
		if (loginExists != null) {
			List<EventJoinedDTO> events = this.eventService.getPastEventsJoined(loginExists);
			if (events.isEmpty()) {
				return new ResponseEntity<List<EventJoinedDTO>>(events, HttpStatus.NOT_FOUND);
			} 
			return new ResponseEntity<List<EventJoinedDTO>>(events, HttpStatus.OK);
		}
		return new ResponseEntity<List<EventJoinedDTO>>(List.of(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/getFutureEventsJoined")
	public ResponseEntity<List<EventJoinedDTO>> getFutureEventsJoined(@RequestBody LoginDTO login) {
		LoginDTO loginExists = this.userService.login(login);
		if (loginExists != null) {
			List<EventJoinedDTO> events = this.eventService.getFutureEventsJoined(loginExists);
			if (events.isEmpty()) {
				return new ResponseEntity<List<EventJoinedDTO>>(events, HttpStatus.NOT_FOUND);
			} 
			return new ResponseEntity<List<EventJoinedDTO>>(events, HttpStatus.OK);
		}
		return new ResponseEntity<List<EventJoinedDTO>>(List.of(), HttpStatus.NOT_ACCEPTABLE);
	}
}
