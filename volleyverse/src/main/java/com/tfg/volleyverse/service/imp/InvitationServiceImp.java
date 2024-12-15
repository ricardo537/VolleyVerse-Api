package com.tfg.volleyverse.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.volleyverse.dto.InvitationSendDTO;
import com.tfg.volleyverse.repository.InvitationRepository;
import com.tfg.volleyverse.service.InvitationService;

@Service
public class InvitationServiceImp implements InvitationService {

	@Autowired
	private InvitationRepository invitationRepository;
	@Autowired
	private UserServiceImp userService;
	@Autowired
	private PlayerServiceImp playerService;
	
	@Override
	public boolean sendInvitation(InvitationSendDTO invitation) {
		
		return false;
	}

}
