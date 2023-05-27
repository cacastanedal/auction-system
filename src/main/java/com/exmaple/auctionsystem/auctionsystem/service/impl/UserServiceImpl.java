package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.UserBo;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.UserDetailsImpl;
import com.exmaple.auctionsystem.auctionsystem.persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    UserBo userBo = repository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.builder()
      .id(userBo.getId())
      .username(userBo.getUsername())
      .email(userBo.getEmail())
      .password(userBo.getPassword())
      .authorities(userBo.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList()))
      .build();
  }

}
