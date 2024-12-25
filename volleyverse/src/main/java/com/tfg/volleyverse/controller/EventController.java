package com.tfg.volleyverse.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.net.httpserver.Authenticator.Success;
import com.tfg.volleyverse.dto.EventRegisterDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.model.Event;
import com.tfg.volleyverse.service.imp.EventServiceImp;
import com.tfg.volleyverse.service.imp.UserServiceImp;

@RestController
@RequestMapping("/volleyverse/api/v1/events")
public class EventController {
	
	@Autowired
	private EventServiceImp eventService;
	@Autowired
	private UserServiceImp userService;
	
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
	
}
