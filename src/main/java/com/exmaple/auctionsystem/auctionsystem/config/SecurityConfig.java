package com.exmaple.auctionsystem.auctionsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class SecurityConfig {

  private static final String ENCODED_ADMIN_PASSWORD = "$2a$12$nPlk4syVCDyWtjx4c410ze8aszs8ouZWbrHDJ.1f2vhoyC41c75du";
  private static final String ENCODED_USER_PASSWORD = "$2a$12$JWQDRFxIdBpYj2HMJ.yl8eoNiz6YzJD0pj4lvX2lbW7gdaQ44nbIa";

  @Bean
  public UserDetailsService users(){

    UserDetails user = User.builder()
      .username("user")
      .password(String.format("{bcrypt}%s", ENCODED_USER_PASSWORD))
      .roles("USER")
      .build();

    UserDetails admin = User.builder()
      .username("admin")
      .password(String.format("{bcrypt}%s", ENCODED_ADMIN_PASSWORD))
      .roles("USER", "ADMIN")
      .build();

    return new InMemoryUserDetailsManager(user,admin);
  }



}
