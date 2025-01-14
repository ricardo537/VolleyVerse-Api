package com.tfg.volleyverse.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.PlayerResumeDTO;
import com.tfg.volleyverse.dto.RegisterPlayerDTO;
import com.tfg.volleyverse.dto.StatsDTO;
import com.tfg.volleyverse.dto.UpdatePlayerDTO;
import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.service.imp.PlayerServiceImp;
import com.tfg.volleyverse.service.imp.StatsServiceImp;
import com.tfg.volleyverse.service.imp.UserServiceImp;

@RestController
@RequestMapping("/volleyverse/api/v1/players")
public class PlayerController {

	@Autowired
	private PlayerServiceImp playerService;
	@Autowired
	private UserServiceImp userService;
	@Autowired
	private StatsServiceImp statsService;
	
	@PostMapping("/register")
	public ResponseEntity<Boolean> registerUser (@RequestBody RegisterPlayerDTO register) {
		LoginDTO foundUser = this.userService.login(new LoginDTO(register.getEmail(), register.getPassword(), "player"));
		Boolean success = false;
		if (foundUser == null) {
			success = this.playerService.registerPlayer(register);
			if (success) {
				return new ResponseEntity<>(success, HttpStatus.OK);
			} 
		} 
		return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);

	}
	
	@PostMapping("/update")
	public ResponseEntity<LoginDTO> updateUser (@RequestBody UpdatePlayerDTO update) {
		LoginDTO success = this.playerService.updatePlayer(update);
		if (success != null) {
			return new ResponseEntity<>(success, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search") 
	public ResponseEntity<List<PlayerResumeDTO>> searchPlayers (@RequestParam String gender, @RequestParam String name) {
		if (name == null || gender == null) {
			return new ResponseEntity<List<PlayerResumeDTO>>(List.of(), HttpStatus.BAD_REQUEST);
		}
		List<PlayerResumeDTO> success = this.playerService.searchPlayers(gender, name);
		if (success.isEmpty()) {
			return new ResponseEntity<List<PlayerResumeDTO>>(success, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PlayerResumeDTO>>(success, HttpStatus.OK);
		
	}
	
	@PostMapping("/updateStats")
	public ResponseEntity<Boolean> updateStats (@RequestBody StatsDTO stats) {
		LoginDTO login = this.userService.login(stats.getLogin());
		if (login != null) {
			boolean success = this.statsService.updateStats(stats);
			if (success) {
				return new ResponseEntity<Boolean>(success, HttpStatus.OK);
			}
			return new ResponseEntity<Boolean>(success, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping("/getStats")
	public ResponseEntity<StatsDTO> getStats (@RequestParam UUID playerId) {
		StatsDTO stats = this.statsService.getStats(playerId);
		if (stats != null) {
			return new ResponseEntity<StatsDTO>(stats, HttpStatus.OK);
		}
		return new ResponseEntity<StatsDTO>(stats, HttpStatus.NOT_FOUND);
	}
	
}
