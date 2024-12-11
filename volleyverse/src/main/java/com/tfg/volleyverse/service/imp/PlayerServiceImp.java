package com.tfg.volleyverse.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterPlayerDTO;
import com.tfg.volleyverse.dto.RegisterUserDTO;
import com.tfg.volleyverse.dto.UpdatePlayerDTO;
import com.tfg.volleyverse.dto.PlayerDTO;
import com.tfg.volleyverse.model.Player;
import com.tfg.volleyverse.model.User;
import com.tfg.volleyverse.repository.PlayerRepository;
import com.tfg.volleyverse.repository.UserRepository;
import com.tfg.volleyverse.service.PlayerService;

@Service
public class PlayerServiceImp implements PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean registerPlayer(RegisterPlayerDTO register) {
		Player player = this.playerRepository.save(new Player(register));
		if (player != null) {
			User user = this.userRepository.save(new User(new RegisterUserDTO(register.getEmail(), register.getPassword(), "player", player.getId())));
			if (user != null) {
				return true;
			} else {
				this.playerRepository.delete(player);
			}
		} 
		return false;
	}

	@Override
	public LoginDTO updatePlayer(UpdatePlayerDTO update) {
		User user = this.userRepository.findByEmailAndPassword(update.getLogin().getEmail(), update.getLogin().getPassword());
		if (user != null) {
			if (!update.getEmail().equals(update.getLogin().getEmail())) {
				User userExist = this.userRepository.findByEmail(update.getEmail());
				if (userExist == null) {
					this.userRepository.delete(user);
					if (update.getEmail() != null && !update.getEmail().equals("")) {
						user.setEmail(update.getEmail());
					}
					if (update.getPassword() != null && !update.getPassword().equals("")) {
						user.setPassword(update.getPassword());
					}
					Optional<Player> playerOp = this.playerRepository.findById(user.getId_user());
					if (playerOp.isPresent()) {
						user = this.userRepository.save(user);
						Player player = playerOp.get();
						player.update(update);
						this.playerRepository.save(player);
						return new LoginDTO(user);
					}
				}
			} else {
				if (update.getPassword() != null && !update.getPassword().equals("")) {
					user.setPassword(update.getPassword());
				}
				Optional<Player> playerOp = this.playerRepository.findById(user.getId_user());
				if (playerOp.isPresent()) {
					user = this.userRepository.save(user);
					Player player = playerOp.get();
					player.update(update);
					this.playerRepository.save(player);
					return new LoginDTO(user);
				}
				
			}
		}
		return null;
	}
	

}
