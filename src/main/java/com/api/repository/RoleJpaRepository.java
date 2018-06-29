package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.api.model.Role;


@Repository
@RepositoryRestResource(exported=false)
public interface RoleJpaRepository extends JpaRepository<Role, Long> {

}
