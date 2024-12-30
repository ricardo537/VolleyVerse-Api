package com.tfg.volleyverse.service.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.DeleteEventDTO;
import com.tfg.volleyverse.dto.EventDTO;
import com.tfg.volleyverse.dto.EventRegisterDTO;
import com.tfg.volleyverse.dto.FilterEventDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.MyEventDTO;
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
import com.tfg.volleyverse.service.EventService;

@Service
public class EventServiceImp implements EventService {
	
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private InscriptionRepository inscriptionRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private PlayRepository playRepository;

	@Override
	public boolean addEvent(EventRegisterDTO eventDTO) {
		LocalDateTime now = LocalDateTime.now();
		if (eventDTO.getStartDate().isAfter(now) && eventDTO.getEndDate().isAfter(eventDTO.getStartDate())) {
			User user = this.userRepository.findByEmailAndPassword(eventDTO.getLogin().getEmail(), eventDTO.getLogin().getPassword());
			if (user != null) {
				Event event = this.eventRepository.save(new Event(eventDTO, user.getIde()));
				if (event != null) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean deleteEvent(DeleteEventDTO event) {
		User user = this.userRepository.findByEmailAndPassword(event.getLogin().getEmail(), event.getLogin().getPassword());
		if (user != null) {
			Optional<Event> eventExists = this.eventRepository.findById(event.getId());
			if (eventExists.isPresent() && eventExists.get().getCreatorId().toString().equals(user.getIde().toString())) {
				List<Inscription> inscriptions = this.inscriptionRepository.findByEventId(eventExists.get().getId());
				inscriptions.stream().forEach(inscription -> {
					this.inscriptionRepository.delete(inscription);
				});
				this.eventRepository.delete(eventExists.get());
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Event> getEventsToSetResults() {
		return this.eventRepository.findByStartDateAfter(LocalDateTime.now());
	}

	@Override
	public List<Event> getEventsToParticipate(String category, String type, String gender) {
		
		return null;
	}

	@Override
	public List<MyEventDTO> getMyEventsToRun(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			List<Event> myEvents = this.eventRepository.findByCreatorId(user.getIde());
			LocalDateTime now = LocalDateTime.now();
			List<MyEventDTO> result = myEvents.stream()
					.filter((e) -> e.getStartDate().isAfter(now))
					.map((event) -> {
						return new MyEventDTO(event);
					}).collect(Collectors.toList());
			return result;
		}
		return List.of();
	}

	@Override
	public List<MyEventDTO> getMyCompletedEvents(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			List<Event> myEvents = this.eventRepository.findByCreatorId(user.getIde());
			LocalDateTime now = LocalDateTime.now();
			List<MyEventDTO> result = myEvents.stream()
					.filter((e) -> e.getStartDate().isBefore(now))
					.map((event) -> {
						return new MyEventDTO(event);
					}).collect(Collectors.toList());
			return result;
		}
		return List.of();
	}

	@Override
	public List<EventDTO> getFilteredEvents(FilterEventDTO filter, int page, int size) {
		LocalDateTime now = LocalDateTime.now();
		if (filter.getStartDate() == null || filter.getStartDate().isBefore(now)) {
			filter.setStartDate(now);
		}
		List<Event> events = this.eventRepository.findByStartDateAfter(filter.getStartDate(), PageRequest.of(page, size)).getContent();
		List<EventDTO> eventsDTO = events.stream()
				.map((event) -> {
					if (this.userCanJoinEvent(event, filter.getLogin())) {
						if (this.isEnrolled(event, filter.getLogin())) {
							return null;
						}
						return new EventDTO(event, this.getNameCreator(event));
					}
					return null;
				}).filter(e -> e != null)
				.collect(Collectors.toList());
		if (events.size() > 0) {
			List<EventDTO> results = eventsDTO.stream()
					.filter((event) -> (event.isValid(filter)))
					.collect(Collectors.toList());
			return results;
		}
		return eventsDTO;
	}
	
	private boolean userCanJoinEvent (Event event, LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			if (user.getType().equals("club")) {
				if (event.getTypeParticipant().equals("player")) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}
	
	private String getNameCreator (Event event) {
		User user = this.userRepository.findByIde(event.getCreatorId());
		if (user != null && user.getType().equals("club")) {
			Optional<Club> club = this.clubRepository.findById(user.getIde());
			if (club.isPresent()) {
				return club.get().getName();
			}
		} else {
			if (user != null && user.getType().equals("player")) {
				Optional<Player> player = this.playerRepository.findById(user.getIde());
				if (player.isPresent()) {
					return player.get().getName() + " " + player.get().getLastName();
				}
			}
		}
		return "";
	}
	
	private boolean isEnrolled(Event event, LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			if (event.getTypeParticipant().equals("player")) {
				Optional<Player> player = this.playerRepository.findById(user.getIde());
				if (player.isPresent()) {
					Optional<Inscription> inscription = this.inscriptionRepository.findByParticipantIdAndEventId(player.get().getId(), event.getId());
					return inscription.isPresent();
				}
			} else {
				return this.userPlaysInTeamsEnrolled(user, event.getId());
			}
		}
		return false;
	}
	
	private boolean userPlaysInTeamsEnrolled (User user, UUID eventId) {
		List<Inscription> inscriptions = this.inscriptionRepository.findByEventId(eventId);
		List<Team> teamsEnrolled = inscriptions.stream()
				.map((inscription) -> {
					Optional<Team> team = this.teamRepository.findById(inscription.getParticipantId());
					if (team.isPresent()) {
						return team.get();
					}
					return null;
				}).filter(i -> i != null)
				.collect(Collectors.toList());
		switch (user.getType()) {
		case "player": {
			List<Boolean> result = teamsEnrolled.stream()
					.map((team) -> {
						Optional<Play> play = this.playRepository.findById(new PlayId(team.getId(), user.getIde()));
						return play.isPresent();
					}).filter(t -> t == true)
					.collect(Collectors.toList());
			if (result.size() > 0) {
				return true;
			}
			break;
		}
		case "club": {
			List<Boolean> result = teamsEnrolled.stream()
					.map((team) -> {
						return team.getClubId().toString().equals(user.getIde().toString());
					}).filter(t -> t == true)
					.collect(Collectors.toList());
			if (result.size() > 0) {
				return true;
			}
			break;
		}
		}
		return false;
	}
	
}
