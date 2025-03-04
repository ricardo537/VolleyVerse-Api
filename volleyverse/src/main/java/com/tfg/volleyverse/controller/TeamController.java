package com.tfg.volleyverse.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tfg.volleyverse.dto.AddPlayerToTeamDTO;
import com.tfg.volleyverse.dto.FilterTeamDTO;
import com.tfg.volleyverse.dto.InvitationAcceptDTO;
import com.tfg.volleyverse.dto.InvitationDTO;
import com.tfg.volleyverse.dto.InvitationSendDTO;
import com.tfg.volleyverse.dto.LeaveTeamDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.PlayerResumeDTO;
import com.tfg.volleyverse.dto.TeamCreationDTO;
import com.tfg.volleyverse.dto.TeamDTO;
import com.tfg.volleyverse.dto.TeamResumeDTO;

public interface TeamController {
	
	@PostMapping("/create")
	public ResponseEntity<String> createTeam(@RequestBody TeamCreationDTO team);
	
	@PostMapping("/addPlayer")
	public ResponseEntity<Boolean> addPlayerToTeam(@RequestBody AddPlayerToTeamDTO playerToTeam);
	
	@GetMapping("/getMembers/{teamId}")
	public ResponseEntity<List<PlayerResumeDTO>> getMembersOfTeam (@PathVariable UUID teamId);
	
	@PostMapping("/getTeams")
	public ResponseEntity<List<TeamDTO>> getTeams (@RequestBody LoginDTO login);
	
	@PostMapping("/leave")
	public ResponseEntity<Boolean> deleteTeam (@RequestBody LeaveTeamDTO data);
	
	@PostMapping("/invite")
	public ResponseEntity<Boolean> invitePlayer (@RequestBody InvitationSendDTO invitation);
	
	@PostMapping("/acceptInvitation")
	public ResponseEntity<Boolean> acceptInvitation(@RequestBody InvitationAcceptDTO invitation);
	
	@PostMapping("/getInvitations")
	public ResponseEntity<List<InvitationDTO>> getInvitations (@RequestBody LoginDTO login);
	
	@PostMapping("/getFilteredTeams")
	public ResponseEntity<List<TeamResumeDTO>> getFilteredTeams (@RequestBody FilterTeamDTO filter);
	
}
