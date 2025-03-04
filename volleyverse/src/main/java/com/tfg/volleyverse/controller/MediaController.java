package com.tfg.volleyverse.controller;

import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface MediaController {

	@PostMapping("upload/profile/{email}")
	public Map<String, String> uploadFile(@RequestParam("file") MultipartFile multipartFile, @PathVariable String email);
	
	@GetMapping("{fileName}")
	public ResponseEntity<Resource> getFile(@PathVariable String fileName);
	
}
