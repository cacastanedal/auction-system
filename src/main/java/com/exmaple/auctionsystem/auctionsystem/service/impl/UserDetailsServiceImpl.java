package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.User;
import com.exmaple.auctionsystem.auctionsystem.persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository repository;

  @Transactional
  public UserDetails loadUserByUsername(String username){
    User user = repository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.builder()
      .id(user.getId())
      .username(user.getUsername())
      .email(user.getEmail())
      .password(user.getPassword())
      .authorities(user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList()))
      .build();
  }

}
