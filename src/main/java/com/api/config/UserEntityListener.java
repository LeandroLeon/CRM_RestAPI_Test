package com.api.config;

import java.util.Collection;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.api.exception.NotOwnerRoleException;

@Service
public class UserEntityListener {
	
	@PreUpdate
	@PrePersist
	public void checkOwnerRole(Object entity) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
		for(GrantedAuthority grantedAuthority : roles) {
			if(grantedAuthority.getAuthority().equals("ROLE_OWNER")) {
				break;
			}
		}
		throw new NotOwnerRoleException("You should be owner to do that");
	}
}
