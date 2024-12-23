package com.tfg.volleyverse.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.LeaveTeamDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.PlayerResumeDTO;
import com.tfg.volleyverse.dto.TeamCreationDTO;
import com.tfg.volleyverse.dto.TeamDTO;
import com.tfg.volleyverse.model.Invitation;
import com.tfg.volleyverse.model.Play;
import com.tfg.volleyverse.model.PlayId;
import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.model.Team;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.InvitationRepository;
import com.tfg.volleyverse.repository.PlayRepository;
import com.tfg.volleyverse.repository.PlayerRepository;
import com.tfg.volleyverse.repository.TeamRepository;
import com.tfg.volleyverse.repository.UserRepository;
import com.tfg.volleyverse.service.TeamService;

@Service
public class TeamServiceImp implements TeamService {

	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlayRepository playRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private InvitationRepository invitationRepository;
	
	@Override
	public UUID createTeam(TeamCreationDTO team) {
		UUID club_id = this.getClubId(team.getLogin());
		team.setClubId(club_id);
		Team teamSaved = this.teamRepository.save(new Team(team));
		if (teamSaved != null) {
			return teamSaved.getId();
		}
		return null;
	}
	
	private UUID getClubId(LoginDTO login) {
		if (login.getType().equals("club")) {
			User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
			if (user != null) {
				return user.getIde();
			}
		}
		return null;
	}

	@Override
	public List<PlayerResumeDTO> getMembersOfTeam(UUID teamId) {
		Optional<Team> team = this.teamRepository.findById(teamId);
		if (team.isPresent()) {
			List<Play> plays = this.playRepository.findByTeamId(teamId);
			List<PlayerResumeDTO> players = plays.stream()
					.map(play -> this.playerRepository.findById(play.getPlayerId()))
					.filter(Optional::isPresent)
					.map(optional -> optional.get())
					.map(player -> {
						PlayerResumeDTO result = new PlayerResumeDTO(player);
						User user = this.userRepository.findByIde(player.getId());
						if (user != null) {
							result.setImg(user.getImg());
						}
						return result;
					}).collect(Collectors.toList());
			return players;
		}
		return List.of();
	}

	@Override
	public List<TeamDTO> getTeams(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			if (user.getType().equals("club")) {
				List<Team> teamsClub = this.teamRepository.findByClubId(user.getIde());
				List<TeamDTO> teams = teamsClub.stream()
						.map(team -> collectClubTeamData(team))
						.collect(Collectors.toList());
				return teams;
			} else {
				List<Play> plays = this.playRepository.findByPlayerId(user.getIde());
				List<TeamDTO> teams = plays.stream()
						.map(play -> collectTeamData(play))
						.filter(p -> p != null)
						.collect(Collectors.toList());
				return teams;
			}
		}
		return List.of();
	}
	
	private TeamDTO collectTeamData(Play play) {
		Team team = this.teamRepository.getById(play.getTeamId());
		if (team != null) {
			List<PlayerResumeDTO> players = getMembersOfTeam(play.getTeamId());
			return new TeamDTO(team, players);
		}
		return null;
	}
	
	private TeamDTO collectClubTeamData(Team team) {
		List<PlayerResumeDTO> players = getMembersOfTeam(team.getId());
		return new TeamDTO(team, players);
	}

	@Override
	public boolean leaveTeam(LeaveTeamDTO data) {
		Team team = this.teamRepository.getById(data.getTeamId());
		User user = this.userRepository.findByEmailAndPassword(data.getLogin().getEmail(), data.getLogin().getPassword());
		if (team != null && user != null) {
			Optional<Play> success = this.playRepository.findById(new PlayId(data.getTeamId(), user.getIde()));
			if (success.isPresent()) {
				List<Play> plays = this.playRepository.findByTeamId(data.getTeamId());
				if (plays.size() == 1) {
					List<Invitation> invitations = this.invitationRepository.findByTeamId(team.getId());
					invitations.forEach(invitation -> {
						this.invitationRepository.delete(invitation);
					});
					this.teamRepository.delete(team);
				}
				this.playRepository.delete(success.get());
				return true;
			}
		}
		return false;
	}

}
