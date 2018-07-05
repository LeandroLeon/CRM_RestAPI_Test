package com.api.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Resource;

import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
	
	private final Path rootLocation = Paths.get("./uploads");
	
	public void store(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("The image couldn´t being stored. Try it again changing the name");
		}
	}
	
	public void store(MultipartFile file, String filename) {
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
		} catch (Exception e) {
			throw new RuntimeException("The image couldn´t being stored. Try it again changing the name");
		}
	}
	
	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = (Resource) new UrlResource(file.toUri());
			if (((AbstractFileResolvingResource) resource).exists() || ((AbstractFileResolvingResource) resource).isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("The fail doesn´t exist");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Couldn´t load the file");
		}
	}
	
	public void deleteFile(Path filepath) {
		try {
			FileSystemUtils.deleteRecursively(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		try {
			if(!Files.exists(rootLocation)) {
				Files.createDirectory(rootLocation);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}

}
