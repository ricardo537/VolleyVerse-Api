package com.tfg.volleyverse.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.ClubDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.PlayerDTO;
import com.tfg.volleyverse.model.Club;
import com.tfg.volleyverse.model.Invitation;
import com.tfg.volleyverse.model.Play;
import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.model.Team;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.ClubRepository;
import com.tfg.volleyverse.repository.InvitationRepository;
import com.tfg.volleyverse.repository.PlayRepository;
import com.tfg.volleyverse.repository.PlayerRepository;
import com.tfg.volleyverse.repository.TeamRepository;
import com.tfg.volleyverse.repository.UserRepository;
import com.tfg.volleyverse.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private PlayRepository playRepository;
	@Autowired
	private InvitationRepository invitationRepository;

	@Override
	public LoginDTO login(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			return new LoginDTO(user);
		}
		return null;
	}

	@Override
	public boolean delete(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			//Cuando pongo otro case "player" no funciona hay que revisarlo
			switch(login.getType()) {
			case ("club") : {
				List<Team> teams = this.teamRepository.findByClubId(user.getIde());
				teams.stream()
				.forEach(team -> {
					this.deleteInvitationsOfTeam(team);
					this.deletePlaysOfTeam(team);
					this.teamRepository.delete(team);
				});
				Optional<Club> club = this.clubRepository.findById(user.getIde());
				if (club.isEmpty()) {
					return false;
				}
				this.clubRepository.delete(club.get());
				break;
			}
			default : {
				List<Play> plays = this.playRepository.findByPlayerId(user.getIde());
				plays.stream().forEach(play -> {
					this.playRepository.delete(play);
					Optional<Team> team = this.teamRepository.findById(play.getTeamId());
					if (team.isPresent()) {
						List<Play> otherPlays = this.playRepository.findByTeamId(play.getTeamId());
						if (otherPlays.size() == 0) {
							this.deleteInvitationsOfTeam(team.get());
							this.deletePlaysOfTeam(team.get());
							this.teamRepository.delete(team.get());
						}
					}
					Optional<Player> player = this.playerRepository.findById(user.getIde());
					if (player.isPresent()) {
						this.deleteInvitationsOfPlayer(player.get());
					}
				});
				Optional<Player> player = this.playerRepository.findById(user.getIde());
				if (player.isEmpty()) {
					return false;
				}
				this.playerRepository.delete(player.get());
				break;
			}
			}
			this.userRepository.delete(user);
			return true;
		}
		return false;
	}

	@Override
	public Object getUserData(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			switch (login.getType()) {
			case "club": {
				Optional<Club> club = this.clubRepository.findById(user.getIde());
				if (club.isEmpty()) {
					return null;
				}
				return new ClubDTO (club.get());
			}
			default : {
				Optional<Player> player = this.playerRepository.findById(user.getIde());
				if (player.isEmpty()) {
					return null;
				}
				return new PlayerDTO (player.get(), user.getImg());
			}
			}
		}
		return null;
	}

	@Override
	public boolean updateProfileImg(String email, String fileName) {
		User user = this.userRepository.findByEmail(email);
		if (user != null) {
			this.userRepository.delete(user);
			user.setImg(fileName);
			this.userRepository.save(user);
			return true;
		}
		return false;
	}
	
	public User findUser(LoginDTO login) {
		return this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
	}
	
	private void deleteInvitationsOfTeam(Team team) {
		List<Invitation> invitations = this.invitationRepository.findByTeamId(team.getId());
		invitations.stream().forEach(invitation -> {
			this.invitationRepository.delete(invitation);
		});
	}
	
	private void deletePlaysOfTeam(Team team) {
		List<Play> plays = this.playRepository.findByTeamId(team.getId());
		plays.stream().forEach(play -> {
			this.playRepository.delete(play);
		});
	}
	
	private void deleteInvitationsOfPlayer(Player player) {
		List<Invitation> invitations = this.invitationRepository.findByUserId(player.getId());
		invitations.stream().forEach(invitation -> {
			this.invitationRepository.delete(invitation);
		});
	}

	@Override
	public UUID getMyId(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if ( user != null) {
			return user.getIde();
		}
		return null;
	}

}
