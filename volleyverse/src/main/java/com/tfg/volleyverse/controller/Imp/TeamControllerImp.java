package com.tfg.volleyverse.controller.Imp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.volleyverse.controller.TeamController;
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
import com.tfg.volleyverse.model.Invitation;
import com.tfg.volleyverse.model.Play;
import com.tfg.volleyverse.service.imp.InvitationServiceImp;
import com.tfg.volleyverse.service.imp.PlayServiceImp;
import com.tfg.volleyverse.service.imp.TeamServiceImp;
import com.tfg.volleyverse.service.imp.UserServiceImp;

@RestController
@RequestMapping("volleyverse/api/v1/teams")
public class TeamControllerImp implements TeamController {

	@Autowired
	private TeamServiceImp teamService;
	@Autowired
	private UserServiceImp userService;
	@Autowired
	private PlayServiceImp playService;
	@Autowired
	private InvitationServiceImp invitationService;
	
	@Override
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
	
	@Override
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
	
	@Override
	public ResponseEntity<List<PlayerResumeDTO>> getMembersOfTeam (@PathVariable UUID teamId) {
		List<PlayerResumeDTO> players = this.teamService.getMembersOfTeam(teamId);
		if (!players.isEmpty()) {
			return new ResponseEntity<List<PlayerResumeDTO>>(players, HttpStatus.OK);
		}
		return new ResponseEntity<List<PlayerResumeDTO>>(List.of(), HttpStatus.NOT_FOUND);
	}
	
	@Override
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
	
	@Override
	public ResponseEntity<Boolean> deleteTeam (@RequestBody LeaveTeamDTO data) {
		LoginDTO login = this.userService.login(data.getLogin());
		if (login != null) {
			Boolean success = this.teamService.leaveTeam(data);
			if (success) {
				return new ResponseEntity<Boolean>(success, HttpStatus.OK);
			} 
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@Override
	public ResponseEntity<Boolean> invitePlayer (@RequestBody InvitationSendDTO invitation) {
		LoginDTO login = this.userService.login(invitation.getLogin());
		if (login != null) {
			Boolean success = this.invitationService.sendInvitation(invitation);
			if (success) {
				return new ResponseEntity<Boolean>(success, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(success, HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<Boolean> acceptInvitation(@RequestBody InvitationAcceptDTO invitation) {
		LoginDTO login = this.userService.login(invitation.getLogin());
		if (login != null) {
			Invitation invit = this.invitationService.acceptInvitation(invitation);
			if (invit != null) {
				Boolean success = this.playService.addPlayer(new Play(invit.getTeamId(), invit.getUserId()));
				if (success) {
					return new ResponseEntity<Boolean>(success, HttpStatus.OK);
				} else {
					return new ResponseEntity<Boolean>(success, HttpStatus.NOT_ACCEPTABLE);
				}
			} 
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<List<InvitationDTO>> getInvitations (@RequestBody LoginDTO login) {
		LoginDTO log = this.userService.login(login);
		if(log != null) {
			List<InvitationDTO> invitations = this.invitationService.getInvitations(login);
			return new ResponseEntity<List<InvitationDTO>>(invitations, HttpStatus.OK);
		}
		return new ResponseEntity<List<InvitationDTO>>(List.of(), HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<List<TeamResumeDTO>> getFilteredTeams (@RequestBody FilterTeamDTO filter) {
		LoginDTO login = this.userService.login(filter.getLogin());
		if (login != null) {
			List<TeamResumeDTO> filteredTeams = this.teamService.getFilteredTeams(filter);
			if (filteredTeams.isEmpty()) {
				return new ResponseEntity<List<TeamResumeDTO>>(filteredTeams, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<TeamResumeDTO>>(filteredTeams, HttpStatus.OK);
		}
		return new ResponseEntity<List<TeamResumeDTO>>(List.of(), HttpStatus.NOT_ACCEPTABLE);
	}
}
