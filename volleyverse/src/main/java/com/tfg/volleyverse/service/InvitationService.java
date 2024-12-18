package com.tfg.volleyverse.service;

import java.util.List;
import java.util.UUID;

import com.tfg.volleyverse.dto.InvitationAcceptDTO;
import com.tfg.volleyverse.dto.InvitationDTO;
import com.tfg.volleyverse.dto.InvitationSendDTO;
import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.model.Invitation;

public interface InvitationService {
	
	public boolean sendInvitation(InvitationSendDTO invitation);
	
	public Invitation acceptInvitation(InvitationAcceptDTO invitation);
	
	public List<InvitationDTO> getInvitations(LoginDTO login);
	
}
