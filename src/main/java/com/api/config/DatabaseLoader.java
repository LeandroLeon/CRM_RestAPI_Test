package com.api.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.api.model.User;
import com.api.repository.UserJpaRepository;

@Component
public class DatabaseLoader implements ApplicationRunner{

	private UserJpaRepository userJpaRepository;
	
	@Autowired
	public DatabaseLoader(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<User> users = Arrays.asList(
                new User("admin", "admin", new String[] {"ROLE_USER", "ROLE_ADMIN"}),
                new User("user", "1234", new String[] {"ROLE_USER"})
        );
        userJpaRepository.saveAll(users);
	}
}
