package com.tfg.volleyverse.service.imp;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.InscriptionDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.model.Club;
import com.tfg.volleyverse.model.Event;
import com.tfg.volleyverse.model.Inscription;
import com.tfg.volleyverse.model.Play;
import com.tfg.volleyverse.model.PlayId;
import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.model.Team;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.ClubRepository;
import com.tfg.volleyverse.repository.EventRepository;
import com.tfg.volleyverse.repository.InscriptionRepository;
import com.tfg.volleyverse.repository.PlayRepository;
import com.tfg.volleyverse.repository.PlayerRepository;
import com.tfg.volleyverse.repository.TeamRepository;
import com.tfg.volleyverse.repository.UserRepository;
import com.tfg.volleyverse.service.InscriptionService;

@Service
public class InscriptionServiceImp implements InscriptionService {
	
	@Autowired
	private InscriptionRepository inscriptionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private PlayRepository playRepository;
	@Autowired
	private ClubRepository clubRepository;
	
	@Override
	public boolean joinEvent(InscriptionDTO inscription) {
		Optional<Inscription> inscriptionExists = this.inscriptionRepository.findByParticipantIdAndEventId(inscription.getParticipantId(), inscription.getEventId());
		if (inscriptionExists.isEmpty()) {
			Optional<Event> eventOp = this.eventRepository.findById(inscription.getEventId());
			if (eventOp.isPresent()) {
				Event event = eventOp.get();
				if (event.getTypeParticipant().equals("player") && this.participantIsLogin(inscription.getLogin(), inscription.getParticipantId())) {
					Inscription result = this.inscriptionRepository.save(new Inscription(inscription));
					if (result != null) {
						return true;
					}
				} else {
					if (this.isValidTeam(event, inscription.getParticipantId())) {
						if (this.isInTheTeam(inscription.getLogin(), inscription.getParticipantId())) {
							Inscription result = this.inscriptionRepository.save(new Inscription(inscription));
							if (result != null) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean unsubscribeEvent(InscriptionDTO inscription) {
		System.out.println(inscription.getParticipantId().toString() + " " + inscription.getEventId());
		Optional<Inscription> inscriptionExists = this.inscriptionRepository.findByParticipantIdAndEventId(inscription.getParticipantId(), inscription.getEventId());
		if (inscriptionExists.isPresent()) {
			System.out.println("La inscripción existe");
			Optional<Event> eventOp = this.eventRepository.findById(inscription.getEventId());
			if (eventOp.isPresent()) {
				Event event = eventOp.get();
				System.out.println("El evento existe");
				if (event.getTypeParticipant().equals("player") && this.participantIsLogin(inscription.getLogin(), inscription.getParticipantId())) {
					this.inscriptionRepository.delete(new Inscription(inscription));
					return true;
				} else {
					if (this.isInTheTeam(inscription.getLogin(), inscription.getParticipantId())) {
						this.inscriptionRepository.delete(new Inscription(inscription));
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean participantIsLogin (LoginDTO login, UUID participantId) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		Optional<Player> player = this.playerRepository.findById(participantId);
		if (player.isPresent()) {
			if (user.getIde().toString().equals(participantId.toString())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isInTheTeam(LoginDTO login, UUID participantId) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		Optional<Team> team = this.teamRepository.findById(participantId);
		if (user.getType().equals("player")) {
			Optional<Play> play = this.playRepository.findById(new PlayId(participantId, user.getIde()));
			if (team.isPresent() && play.isPresent()) {
				return true;
			}
		} else {
			Optional<Club> club = this.clubRepository.findById(user.getIde());
			if (team.isPresent() && club.isPresent() && club.get().getId().toString().equals(team.get().getClubId().toString())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isValidTeam (Event event, UUID teamId) {
		Optional<Team> team = this.teamRepository.findById(teamId);
		if (team.isPresent()) {
			return (event.getTypeParticipant().equals(team.get().getType()) && event.getGender().equals(team.get().getGender()) && event.getCategory().equals(team.get().getCategory()));
		}
		return false;
	}

}
