package com.tfg.volleyverse.service.imp;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import com.tfg.volleyverse.dto.EventJoinedDTO;
import com.tfg.volleyverse.dto.EventRegisterDTO;
import com.tfg.volleyverse.dto.FilterEventDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.MyEventDTO;
import com.tfg.volleyverse.dto.ParticipantsDTO;
import com.tfg.volleyverse.dto.PlayerResumeDTO;
import com.tfg.volleyverse.dto.ResumeDTO;
import com.tfg.volleyverse.dto.TeamResumeDTO;
import com.tfg.volleyverse.dto.UpdateEventDTO;
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
						return new MyEventDTO(event, this.getParticipant(user, event));
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
						return new MyEventDTO(event, this.getParticipant(user, event));
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
						if (team.getClubId() != null) {
							return team.getClubId().toString().equals(user.getIde().toString());
						}
						return false;
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

	@Override
	public List<ResumeDTO> getParticipants(ParticipantsDTO participantsData) {
		Optional<Event> event = this.eventRepository.findById(participantsData.getEventId());
		if (event.isPresent()) {
			List<Inscription> inscriptions = this.inscriptionRepository.findByEventId(participantsData.getEventId());
			if (!inscriptions.isEmpty() && event.get().getTypeParticipant().equals("player")) {
				List<ResumeDTO> participants = inscriptions.stream()
						.map(i -> {
							Optional<Player> player = this.playerRepository.findById(i.getParticipantId());
							if (player.isPresent()) {
								return new PlayerResumeDTO(player.get());
							}
							return null;
						}).filter(p -> p != null)
						.collect(Collectors.toList());
				return participants;
			} else {
				List<ResumeDTO> participants = inscriptions.stream()
						.map(i -> {
							Optional<Team> team = this.teamRepository.findById(i.getParticipantId());
							if (team.isPresent()) {
								List<Play> plays = this.playRepository.findByTeamId(team.get().getId());
								List<PlayerResumeDTO> members = plays.stream()
										.map(play -> {
											Optional<Player> player = this.playerRepository.findById(play.getPlayerId());
											if (player.isPresent()) {
												return new PlayerResumeDTO(player.get());
											} 
											return null;
										}).filter(member -> member != null)
										.collect(Collectors.toList());
								return new TeamResumeDTO(team.get(), members);
							}
							return null;
						}).filter(p -> p != null)
						.collect(Collectors.toList());
				return participants;
			}
		}
		return List.of();
	}

	
	private List<EventJoinedDTO> getEventsJoined(LoginDTO login) {
		User user = this.userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (user != null) {
			switch (user.getType()) {
			case "player": {
				List<Event> eventsAlone = this.getEventsJoinedAlone(user.getIde());
				List<Event> eventsWithTeams = this.getEventsJoinedWithTeam(user.getIde(), user.getType());
				eventsAlone.addAll(eventsWithTeams);
				List<EventJoinedDTO> events = eventsAlone.stream()
						.map(e -> {
							EventJoinedDTO event = new EventJoinedDTO(e, this.getNameCreator(e), this.getParticipant(user, e));
							return event;
						}).collect(Collectors.toList());
				return events;
			}
			case "club": {
				List<Event> eventsOfTeams = this.getEventsJoinedWithTeam(user.getIde(), user.getType());
				List<EventJoinedDTO> events = eventsOfTeams.stream()
						.map(e -> {
							EventJoinedDTO event = new EventJoinedDTO(e, this.getNameCreator(e), this.getParticipant(user, e));
							return event;
						}).collect(Collectors.toList());
				return events;
			}
			}
		}
		return List.of();
	}
	
	private UUID getParticipant(User user, Event event) {
		List<Inscription> inscriptions = this.inscriptionRepository.findByEventId(event.getId());
		UUID participantId = null;
		if (event.getTypeParticipant().equals("player")) {
			return user.getIde();
		} else {
			if (user.getType().equals("player")) {
				List<Play> plays = this.playRepository.findByPlayerId(user.getIde());
				List<Team> teams = plays.stream()
						.map(p -> {
							Optional<Team> team = this.teamRepository.findById(p.getTeamId());
							if (team.isPresent()) {
								return team.get();
							}
							return null;
						}).filter(t -> t != null)
						.collect(Collectors.toList());
				List<UUID> participants = inscriptions.stream().map(i -> {
					for (Team team: teams) {
						if (team.getId().toString().equals(i.getParticipantId().toString())) {
							return i.getParticipantId();
						}
					}
					return null;
				}).filter(p -> p != null)
				.collect(Collectors.toList());
				if (participants.size() > 0) 
				participantId = participants.get(0);
			} else {
				List<Team> teams = this.teamRepository.findByClubId(user.getIde());
				List<UUID> participants = inscriptions.stream().map(i -> {
					for (Team team: teams) {
						if (team.getId().toString().equals(i.getParticipantId().toString())) {
							return i.getParticipantId();
						}
					}
					return null;
				}).filter(p -> p != null)
				.collect(Collectors.toList());
				participantId = participants.get(0);
			}
		}
		return participantId;
	}
	
	private List<Event> getEventsJoinedAlone(UUID userId) {
		List<Inscription> inscriptions = this.inscriptionRepository.findByParticipantId(userId);
		List<Event> events = inscriptions.stream()
				.map(i -> {
					Optional<Event> event = this.eventRepository.findById(i.getEventId());
					if (event.isPresent()) {
						return event.get();
					}
					return null;
				}).filter(e -> e != null)
				.collect(Collectors.toList());
		return events;
	}
	
	private List<Event> getEventsJoinedWithTeam(UUID userId, String type) {
		if (type.equals("player")) {
			List<Play> plays = this.playRepository.findByPlayerId(userId);
			List<Team> teams = plays.stream()
					.map(p -> {
						Optional<Team> team = this.teamRepository.findById(p.getTeamId());
						if (team.isPresent()) {
							return team.get();
						}
						return null;
					}).filter(t -> t != null)
					.collect(Collectors.toList());
			List<Event> events = new ArrayList();
			teams.stream().forEach(team -> {
				List<Inscription> inscriptions = this.inscriptionRepository.findByParticipantId(team.getId());
				inscriptions.stream().forEach(i -> {
					Optional<Event> event = this.eventRepository.findById(i.getEventId());
					if (event.isPresent()) {
						events.add(event.get());
					}
				});
			});
			return events;
		} else {
			if (type.equals("club")) {
				List<Team> teams = this.teamRepository.findByClubId(userId);
				List<Event> events = new ArrayList();
				teams.stream().forEach(team -> {
					List<Inscription> inscriptions = this.inscriptionRepository.findByParticipantId(team.getId());
					inscriptions.stream().forEach(i -> {
						Optional<Event> event = this.eventRepository.findById(i.getEventId());
						if (event.isPresent()) {
							events.add(event.get());
						}
					});
				});
				return events;
			}
		}
		return List.of();	}

	@Override
	public List<EventJoinedDTO> getPastEventsJoined(LoginDTO login) {
		List<EventJoinedDTO> events = this.getEventsJoined(login);
		LocalDateTime now = LocalDateTime.now();
		List<EventJoinedDTO> result = events.stream().filter(e -> e.getStartDate().isBefore(now)).collect(Collectors.toList());
		return result;
	}

	@Override
	public List<EventJoinedDTO> getFutureEventsJoined(LoginDTO login) {
		List<EventJoinedDTO> events = this.getEventsJoined(login);
		LocalDateTime now = LocalDateTime.now();
		List<EventJoinedDTO> result = events.stream().filter(e -> e.getStartDate().isAfter(now)).collect(Collectors.toList());
		return result;
	}

	@Override
	public boolean updateEvent(UpdateEventDTO event) {
		User user = this.userRepository.findByEmail(event.getLogin().getEmail());
		Event eventUpdated = new Event(event, user.getIde());
		Event success = this.eventRepository.save(eventUpdated);
		if (success != null) {
			return true;
		}
		return false;
	}
	
}
