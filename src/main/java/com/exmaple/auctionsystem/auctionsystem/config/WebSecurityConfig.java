package com.exmaple.auctionsystem.auctionsystem.config;

import com.exmaple.auctionsystem.auctionsystem.jwt.AuthEntryPointJwt;
import com.exmaple.auctionsystem.auctionsystem.jwt.AuthTokenFilter;
import com.exmaple.auctionsystem.auctionsystem.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  final private UserServiceImpl userDetailsService;

  final private AuthEntryPointJwt unauthorizedHandler;

  final private AuthTokenFilter authTokenFilter;

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();

  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());

    return authenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeHttpRequests()
      .requestMatchers("/auth/**").permitAll() //TODO: Refine request matchers 1
      //.requestMatchers("/**").permitAll() //TODO: Refine request matchers 2
      .anyRequest().authenticated();

    http.authenticationProvider(authenticationProvider());

    http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }


}
