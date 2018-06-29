package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.Customer;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

}
