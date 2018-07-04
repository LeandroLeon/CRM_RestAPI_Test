package com.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.api.model.User;

@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class JpaConfiguration {
	
	@Bean
    AuditorAware<User> auditorProvider() {
        return new SecurityAuditorAware();
    }
}
