package com.tfg.volleyverse.controllerImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.volleyverse.controller.ClubController;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.dto.RegisterClubDTO;
import com.tfg.volleyverse.dto.UpdateClubDTO;
import com.tfg.volleyverse.service.imp.ClubServiceImp;
import com.tfg.volleyverse.service.imp.UserServiceImp;

@RestController
@RequestMapping("volleyverse/api/v1/clubs")
public class ClubControllerImp implements ClubController {
	
	@Autowired
	private ClubServiceImp clubService;
	@Autowired
	private UserServiceImp userService;
	
	@Override
	public ResponseEntity<Boolean> registerClub (@RequestBody RegisterClubDTO register) {
		LoginDTO foundUser = this.userService.login(new LoginDTO(register.getEmail(), register.getPassword(), "club"));
		Boolean success = false;
		if (foundUser == null) {
			success = this.clubService.registerClub(register);
			if (success) {
				return new ResponseEntity<>(success, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<LoginDTO> updateClub (@RequestBody UpdateClubDTO update) {
		LoginDTO success = this.clubService.updateClub(update);
		if (success != null) {
			return new ResponseEntity<>(success, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
		}
	}
}
