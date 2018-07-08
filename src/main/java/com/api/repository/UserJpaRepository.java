package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.api.model.User;

@Repository("userJpaRepository")
public interface UserJpaRepository extends JpaRepository<User, Long> {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	User findByUsername(String username);
}

