package com.tfg.volleyverse.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.TeamCreationDTO;
import com.tfg.volleyverse.model.Play;
import com.tfg.volleyverse.service.imp.PlayServiceImp;
import com.tfg.volleyverse.service.imp.TeamServiceImp;
import com.tfg.volleyverse.service.imp.UserServiceImp;

@RestController
@RequestMapping("volleyverse/api/v1/teams")
public class TeamController {

	@Autowired
	private TeamServiceImp teamService;
	@Autowired
	private UserServiceImp userService;
	@Autowired
	private PlayServiceImp playService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createTeam(@RequestBody TeamCreationDTO team) {
		LoginDTO user = this.userService.login(team.getLogin());
		if (user != null) {
			UUID success = this.teamService.createTeam(team);
			if (success != null) {
				if (team.getLogin().getType().equals("user")) {
					UUID userId = this.userService.findUser(user).getIde();
					this.playService.addPlayer(new Play(success, userId));
				}
				return new ResponseEntity<String>(success.toString(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}
}
