package com.tfg.volleyverse.service;

import com.tfg.volleyverse.dto.InvitationSendDTO;

public interface InvitationService {
	
	public boolean sendInvitation(InvitationSendDTO invitation);
	
}
