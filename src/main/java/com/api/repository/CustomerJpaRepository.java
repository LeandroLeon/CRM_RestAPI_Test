package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.api.model.Customer;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

}
