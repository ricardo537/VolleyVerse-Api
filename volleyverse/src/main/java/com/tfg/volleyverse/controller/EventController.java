package com.tfg.volleyverse.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

public interface EventController {

	@PostMapping("/add")
	public ResponseEntity<Boolean> addEvent (@RequestBody EventRegisterDTO event);
	
	@PostMapping("/getMyEventsToRun")
	public ResponseEntity<List<MyEventDTO>> getMyEventsToRun (@RequestBody LoginDTO login);
	
	@PostMapping("/getMyCompletedEvents")
	public ResponseEntity<List<MyEventDTO>> getMyCompletedEvents (@RequestBody LoginDTO login);
	
	@PostMapping("/deleteEvent")
	public ResponseEntity<Boolean> deleteEvent(@RequestBody DeleteEventDTO event);
	
	@PostMapping("/getEventsFiltered")
	public ResponseEntity<List<EventDTO>> getFilteredEvents (@RequestBody FilterEventDTO filter, @RequestParam int page, @RequestParam int size);
	
	@PostMapping("/join")
	public ResponseEntity<Boolean> joinEvent (@RequestBody InscriptionDTO inscription);
	
	@PostMapping("/unsubscribe")
	public ResponseEntity<Boolean> unsubscribeEvent (@RequestBody InscriptionDTO inscription);
	
	@PostMapping("/getParticipants")
	public ResponseEntity<List<ResumeDTO>> getParticipants(@RequestBody ParticipantsDTO participantsData);
	
	@PostMapping("/getPastEventsJoined")
	public ResponseEntity<List<EventJoinedDTO>> getPastEventsJoined(@RequestBody LoginDTO login);
	
	@PostMapping("/getFutureEventsJoined")
	public ResponseEntity<List<EventJoinedDTO>> getFutureEventsJoined(@RequestBody LoginDTO login);
	
}
