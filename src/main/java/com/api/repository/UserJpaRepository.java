package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.api.model.User;

@Repository("userJpaRepository")
public interface UserJpaRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	@PreAuthorize("hasRole('ROLE_OWNER')")
	void delete(User user);
	
	@PreAuthorize("hasRole('ROLE_OWNER')")
	void deleteAll();
	
	@PreAuthorize("hasRole('ROLE_OWNER')")
	void deleteAll(Iterable<? extends User> entities);
	
	
}

