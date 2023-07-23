package com.yolo.domain.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {

  @Bean
  @ConditionalOnMissingBean(UserDetailsService.class)
  InMemoryUserDetailsManager inMemoryUserDetailsManager() { 
    String generatedPassword = "password";
    return new InMemoryUserDetailsManager(User.withUsername("user")
        .password(generatedPassword).roles("ROLE_USER").build());
  }

  @Bean
  @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
  DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
    return new DefaultAuthenticationEventPublisher(delegate);
  }

}
