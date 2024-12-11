package com.tfg.volleyverse.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface StorageService {

	void init();
	
	String store(MultipartFile file, String email);
	
	Resource loadAsResource(String fileName);
}
