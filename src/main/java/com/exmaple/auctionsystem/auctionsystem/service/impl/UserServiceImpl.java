package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.UserBo;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.UserDetailsImpl;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.UserUpdateDto;
import com.exmaple.auctionsystem.auctionsystem.persistence.UserRepository;
import com.exmaple.auctionsystem.auctionsystem.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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

  @Override
  public Page<UserBo> getAllUsersByPage(Pageable pageDetails){
    return repository.findAll(pageDetails);
  }

  @Override
  public UserBo getUserById(Long id){
    return repository.findById(id).orElseThrow(() -> getNotFoundError(id));
  }

  @Override
  public UserBo updateUser(Long id, UserUpdateDto dto){
    UserBo userBo = repository.findById(id).orElseThrow(() -> getNotFoundError(id));

    userBo.setNickName(dto.getNickName());
    userBo.setPersonalIdentification(dto.getPersonalIdentification());

    return repository.save(userBo);
  }

  @Override
  public List<UserBo> findUserWithMostItems(Pageable pageable){
    return repository.findUserWithMostItems(pageable);
  }

  private EntityNotFoundException getNotFoundError(Long id){
    return new EntityNotFoundException(String.format("User with id %s doesn't exist", id));
  }

}
