package com.exmaple.auctionsystem.auctionsystem.service;

import com.exmaple.auctionsystem.auctionsystem.domain.UserBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.UserUpdateDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

  List<UserBo> getAllUsers();

  UserBo getUserById(Long id);

  UserBo updateUser(Long id, UserUpdateDto dto);

}
