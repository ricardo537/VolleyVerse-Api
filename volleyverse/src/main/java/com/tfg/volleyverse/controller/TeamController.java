package com.tfg.volleyverse.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.volleyverse.dto.AddPlayerToTeamDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.PlayerResumeDTO;
import com.tfg.volleyverse.dto.TeamCreationDTO;
import com.tfg.volleyverse.dto.TeamDTO;
import com.tfg.volleyverse.model.Play;
import com.tfg.volleyverse.model.User;
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
				if (user.getType().equals("player")) {
					UUID userId = this.userService.findUser(user).getIde();
					this.playService.addPlayer(new Play(success, userId));
				}
				return new ResponseEntity<String>(success.toString(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/addPlayer")
	public ResponseEntity<Boolean> addPlayerToTeam(@RequestBody AddPlayerToTeamDTO playerToTeam) {
		LoginDTO user = this.userService.login(playerToTeam.getLogin());
		if (user != null) {
			Boolean success = this.playService.addPlayer(new Play(playerToTeam.getTeamId(), playerToTeam.getPlayerId()));
			if (success) {
				return new ResponseEntity<Boolean>(success, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(success, HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/getMembers/{teamId}")
	public ResponseEntity<List<PlayerResumeDTO>> getMembersOfTeam (@PathVariable UUID teamId) {
		List<PlayerResumeDTO> players = this.teamService.getMembersOfTeam(teamId);
		if (!players.isEmpty()) {
			return new ResponseEntity<List<PlayerResumeDTO>>(players, HttpStatus.OK);
		}
		return new ResponseEntity<List<PlayerResumeDTO>>(List.of(), HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/getTeams")
	public ResponseEntity<List<TeamDTO>> getTeams (@RequestBody LoginDTO login) {
		LoginDTO user = this.userService.login(login);
		if (user != null) {
			List<TeamDTO> success = this.teamService.getTeams(login);
			if (success.isEmpty()) {
				return new ResponseEntity<List<TeamDTO>>(success, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<TeamDTO>>(success, HttpStatus.OK);
		}
		return new ResponseEntity<List<TeamDTO>>(List.of(), HttpStatus.NOT_FOUND);
	}
}
