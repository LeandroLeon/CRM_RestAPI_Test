package com.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.api.model.User;
import com.api.repository.UserJpaRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userJpaRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username + " was not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRoles())
        );
	}
}
