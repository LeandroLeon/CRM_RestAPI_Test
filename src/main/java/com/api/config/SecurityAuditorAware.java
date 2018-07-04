package com.api.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.api.model.User;
import com.api.repository.UserJpaRepository;

@Component
public class SecurityAuditorAware implements AuditorAware<User> {
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Override
	public Optional<User> getCurrentAuditor() {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    if (authentication == null || !authentication.isAuthenticated()) {
		    	return null;
		    }
		    String username = authentication.getName();
		    return Optional.ofNullable(userJpaRepository.findByUsername(username));
		  }
}
