package com.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.api.handler.UserEventHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@EntityListeners(UserEventHandler.class)
public class User {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder(6);
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	private String[] roles;

	public User() {
		
	}
	 
	public User(String username, String password, String[] roles) {
		super();
		this.username = username;
		setPassword(password);
		this.roles = roles;
	}
	
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonSetter
	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
	public boolean isAdmin() {
		for(String role : this.roles) {
			if(role.equals("ROLE_ADMIN"))return true;
		}
		return false;
	}
	
	public boolean isOwner() {
		for(String role : this.roles) {
			if(role.equals("ROLE_OWNER"))return true;
		}
		return false;
	}
	
}
