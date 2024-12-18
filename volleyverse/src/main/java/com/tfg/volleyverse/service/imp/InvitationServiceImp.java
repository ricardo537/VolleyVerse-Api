package com.tfg.volleyverse.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.InvitationAcceptDTO;
import com.tfg.volleyverse.dto.InvitationDTO;
import com.tfg.volleyverse.dto.InvitationSendDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.model.Club;
import com.tfg.volleyverse.model.Invitation;
import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.model.Team;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.ClubRepository;
import com.tfg.volleyverse.repository.InvitationRepository;
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
	
	@Override
	public boolean sendInvitation(InvitationSendDTO invitation) {
		User user = this.userRepository.findByEmail(invitation.getLogin().getEmail());
		Optional<Team> team = this.teamRepository.findById(invitation.getTeamId());
		if (user != null && team.isPresent()) {
			Invitation invitationSaved = this.invitationRepository.save(new Invitation(invitation, user.getIde()));
			Invitation result = this.invitationRepository.save(invitationSaved);
			if (result != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Invitation acceptInvitation(InvitationAcceptDTO invitation) {
		Optional<Invitation> invit = this.invitationRepository.findById(invitation.getId());
		if (invit.isPresent()) {
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
						User us = this.userRepository.findByIde(invit.getUserId());
						if (us != null) {
							if (us.getType().equals("club")) {
								Optional<Club> club = this.clubRepository.findById(invit.getTeamId());
								if (club.isPresent()) {
									return new InvitationDTO(invit, club.get().getName());
								} 
							} else {
								if (us.getType().equals("player")) {
									Optional<Player> player = this.playerRepository.findById(invit.getUserId());
									if (player.isPresent()) {
										return new InvitationDTO(invit, player.get().getName() + " " + player.get().getLastName());
									}
								}
							}
						}
						return null;
					}).filter(i -> i != null)
					.collect(Collectors.toList());
		}
		return List.of();
	}

}
