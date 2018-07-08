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
	
	@JsonIgnore
	public boolean isAdmin() {
		for(String role : this.roles) {
			if(role.equals("ROLE_ADMIN"))return true;
		}
		return false;
	}
	
	@JsonIgnore
	public boolean isOwner() {
		for(String role : this.roles) {
			if(role.equals("ROLE_OWNER"))return true;
		}
		return false;
	}
	
	@JsonIgnore
	public boolean isUser() {
		for(String role : this.roles) {
			if(role.equals("ROLE_USER"))return true;
		}
		return false;
	}
	
	public boolean haveRole(String role) {
		for(String auxRole : this.roles) {
			if(role.equals(auxRole))return true;
		}
		return false; 
	}
	
	public void setRole(String role) {
		if(this.roles == null) {
			this.roles = new String[1];
			this.roles[0] = role;
		}
		
		if(this.haveRole(role))return;
		String[] roles = this.roles;
		String[] result = new String[roles.length + 1];
		int i = 0;
		for(; i < roles.length; i++) {
			result[i] = roles[i];
		}
		result[i]=role;
		this.roles = result;
	}
	
	public void deleteRole(String role) {
		if(!this.haveRole(role))return;
		String[] roles = this.roles;
		String[] result = new String[roles.length -1];
		for(int rolesIndex = 0,resultIndex = 0; rolesIndex < roles.length; rolesIndex++) {
			if(roles[rolesIndex].equals(role))continue;
			result[resultIndex] = roles[rolesIndex];
			resultIndex++;
		}
		this.roles = result;
	}
}
