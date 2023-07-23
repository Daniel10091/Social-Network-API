package com.yolo.domain.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

  // @Bean
  // UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
  //   InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
  //   manager.createUser(User.withUsername("user")
  //       .password(bCryptPasswordEncoder.encode("userPass"))
  //       .roles("USER")
  //       .build());
  //   manager.createUser(User.withUsername("admin")
  //       .password(bCryptPasswordEncoder.encode("adminPass"))
  //       .roles("USER", "ADMIN")
  //       .build());
  //   return manager;
  // }

}
