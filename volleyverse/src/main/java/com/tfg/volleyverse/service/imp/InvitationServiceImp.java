package com.tfg.volleyverse.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.InvitationAcceptDTO;
import com.tfg.volleyverse.dto.InvitationDTO;
import com.tfg.volleyverse.dto.InvitationSendDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.model.Club;
import com.tfg.volleyverse.model.Invitation;
import com.tfg.volleyverse.model.Play;
import com.tfg.volleyverse.model.PlayId;
import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.model.Team;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.ClubRepository;
import com.tfg.volleyverse.repository.InvitationRepository;
import com.tfg.volleyverse.repository.PlayRepository;
import com.tfg.volleyverse.repository.PlayerRepository;
import com.tfg.volleyverse.repository.TeamRepository;
import com.tfg.volleyverse.repository.UserRepository;
import com.tfg.volleyverse.service.InvitationService;

@Service
public class InvitationServiceImp implements InvitationService {

	@Autowired
	private InvitationRepository invitationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private PlayRepository playRepository;
	
	@Override
	public boolean sendInvitation(InvitationSendDTO invitation) {
		User user = this.userRepository.findByEmail(invitation.getLogin().getEmail());
		Optional<Team> team = this.teamRepository.findById(invitation.getTeamId());
		if (user != null && team.isPresent()) {
			Invitation invitationExists = this.invitationRepository.findByUserIdAndTeamId(invitation.getUserId(), invitation.getTeamId());
			Optional<Play> play = this.playRepository.findById(new PlayId(invitation.getTeamId(), invitation.getUserId()));
			if (play.isPresent()) {
				return false;
			}
			if (invitationExists != null) {
				return true;
			}
			Invitation result = this.invitationRepository.save(new Invitation(invitation, user.getIde()));
			if (result != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Invitation acceptInvitation(InvitationAcceptDTO invitation) {
		User user = this.userRepository.findByEmailAndPassword(invitation.getLogin().getEmail(), invitation.getLogin().getPassword());
		Optional<Invitation> invit = this.invitationRepository.findById(invitation.getId());
		if (invit.isPresent() && invit.get().getUserId().toString().equals(user.getIde().toString())) {
			this.invitationRepository.delete(invit.get());
			if (invitation.getState()) {
				return invit.get();
			}
		}
		return null;
	}

	@Override
	public List<InvitationDTO> getInvitations(LoginDTO login) {
		User user = this.userRepository.findByEmail(login.getEmail());
		if (user != null) {
			List<Invitation> invitations = this.invitationRepository.findByUserId(user.getIde());
			List<InvitationDTO> result = invitations.stream()
					.map(invit -> {
						User us = this.userRepository.findByIde(invit.getHost());
						Optional<Team> team = this.teamRepository.findById(invit.getTeamId());
						if (us != null && team.isPresent()) {
							if (us.getType().equals("club")) {
								Optional<Club> host = this.clubRepository.findById(us.getIde());
								if (host.isPresent()) {
									return new InvitationDTO(invit, host.get().getName(), team.get().getName());
								} 
							} else {
								if (us.getType().equals("player")) {
									Optional<Player> host = this.playerRepository.findById(us.getIde());
									Optional<Player> player = this.playerRepository.findById(invit.getUserId());
									if (player.isPresent() && host.isPresent()) {
										return new InvitationDTO(invit, host.get().getName() + " " + host.get().getLastName(), team.get().getName());
									}
								}
							}
						}
						return null;
					}).filter(i -> i != null)
					.collect(Collectors.toList());
			return result;
		}
		return List.of();
	}

}
