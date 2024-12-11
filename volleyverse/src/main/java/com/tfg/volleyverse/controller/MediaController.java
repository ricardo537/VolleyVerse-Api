package com.tfg.volleyverse.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tfg.volleyverse.dto.LoginDTO;
import com.tfg.volleyverse.service.imp.StorageServiceImp;
import com.tfg.volleyverse.service.imp.UserServiceImp;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/volleyverse/api/v1/media")
public class MediaController {
	
	@Autowired
	private StorageServiceImp storageService;
	@Autowired
	private UserServiceImp userService;
	@Autowired
	private HttpServletRequest request;
	
	
	@PostMapping("upload/profile/{email}")
	public Map<String, String> uploadFile(@RequestParam("file") MultipartFile multipartFile, @PathVariable String email) {
		String path = this.storageService.store(multipartFile, email);
		String[] paths = path.split("/");
		boolean success = this.userService.updateProfileImg(email, paths[paths.length-1]);
		if (success) {
			String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
			String url = ServletUriComponentsBuilder
					.fromHttpUrl(host)
					.path("/volleyverse/api/v1/media/")
					.path(path)
					.toUriString();
			return Map.of("url", url);
		}
		return Map.of("url", "");
	}
	
	@GetMapping("{fileName}")
	public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
		try {
			Resource file = this.storageService.loadAsResource(fileName);
			String contentType = Files.probeContentType(file.getFile().toPath());
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
		} catch (IOException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		
	}
}
