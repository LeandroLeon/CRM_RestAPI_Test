package com.api;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.api.service.StorageService;

@SpringBootApplication
@EnableJpaRepositories(basePackages= {"com.api.repository"})
@ComponentScan(basePackages = {"com.api", "com.api.config", "com.api.repository"})
public class RestApiApplication implements CommandLineRunner{

	@Resource
	StorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.init();
		
	}
}
