package com.tfg.volleyverse.service.imp;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tfg.volleyverse.service.StorageService;

import jakarta.annotation.PostConstruct;

@Service
public class StorageServiceImp implements StorageService {

	@Value("${media.location}")
	private String mediaLocation;
	
	private Path rootLocation;
	
	@Override
	@PostConstruct
	public void init() {
		rootLocation = Paths.get(mediaLocation);
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String store(MultipartFile file, String email) {
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file");
			}
			String fileName = email + file.getOriginalFilename();
			Path destinationFile = rootLocation.resolve(Paths.get(fileName))
					.normalize().toAbsolutePath();
			try (InputStream inputStream = file.getInputStream()){
				
				Files.copy(inputStream,  destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
			return fileName;
		}  catch (Exception e) {
			throw new RuntimeException("Hubo un error al conectar el inputStream" + e);
		}
	}

	@Override
	public Resource loadAsResource(String fileName) {
		try {
			Path file = rootLocation.resolve(fileName);
			Resource resource = new UrlResource((file.toUri()));
			
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read file:" + fileName);
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Could not read file:" + fileName);
		}
	}

}
