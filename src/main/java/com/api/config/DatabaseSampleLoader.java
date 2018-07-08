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
public class DatabaseSampleLoader implements ApplicationRunner{

	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(userJpaRepository.findByUsername("admin")!=null){
			
		}else{
			 List<User> users = Arrays.asList(
                new User("admin", "admin", new String[] {"ROLE_USER", "ROLE_ADMIN"}),
                new User("user", "1234", new String[] {"ROLE_USER"}),
                new User("owner", "owner", new String[] {"ROLE_USER", "ROLE_ADMIN", "ROLE_OWNER"})
					);
        
			userJpaRepository.saveAll(users);
		}
	}
}
