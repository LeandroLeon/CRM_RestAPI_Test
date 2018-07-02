package com.api.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.api.repository.UserJpaRepository;

public class SecurityAuditorAware implements AuditorAware<String> {

	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication==null || !authentication.isAuthenticated()) {
			return null;
		} else {
			String username = authentication.getName();
			return Optional.ofNullable(userJpaRepository.findByUsername(username).getUsername());
		}
	}
}
