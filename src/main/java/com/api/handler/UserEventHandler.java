package com.api.handler;

import java.util.Collection;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.api.exception.ForbiddenActionException;
import com.api.exception.MoreThanOneOwnerException;
import com.api.model.User;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {
	
	@PreAuthorize("hasRole('ROLE_OWNER')")
	@HandleBeforeCreate
	public void checkMoreThanOneOwner(User user) {
		if(user.isOwner()) {
			throw new MoreThanOneOwnerException("It is forbbiden more than one owner person");
		} 
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
	@HandleBeforeDelete
	public void checkDeletedUser(User user) {
		if(user.isAdmin()) {
			Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
			Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
			for(GrantedAuthority role : roles) {
				if(role.getAuthority().toString().equals("ROLE_OWNER")) {
					return;
				}
			}
			throw new ForbiddenActionException();
		} else if (user.isOwner()) {
			throw new ForbiddenActionException();
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
	@HandleBeforeSave
	public void checkUpdateUser(User user) {
		if(user.isAdmin()) {
			if(loggedUserIsOwner())return;
			throw new ForbiddenActionException();
		}
		else if (user.isOwner()) {
			if(loggedUserIsOwner())return;
			throw new ForbiddenActionException();
		}
}
	
	public boolean loggedUserIsOwner() {
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
		for(GrantedAuthority role : roles) {
			if(role.getAuthority().toString().equals("ROLE_OWNER"))return true;
		}
		return false;
	}
}
