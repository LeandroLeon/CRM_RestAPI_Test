package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}

