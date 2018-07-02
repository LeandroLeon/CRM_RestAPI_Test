package com.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages= {"com.api.repository"})
public class JpaConfiguration {
	
	@Bean
    AuditorAware<String> auditorProvider() {
        return new SecurityAuditorAware();
    }
}
