package com.tfg.volleyverse.service;

import java.util.UUID;

import com.tfg.volleyverse.dto.InscriptionDTO;
import com.tfg.volleyverse.dto.LoginDTO;

public interface InscriptionService {

	boolean joinEvent (InscriptionDTO inscription);
	
	boolean unsubscribeEvent (InscriptionDTO inscription);
	
}
