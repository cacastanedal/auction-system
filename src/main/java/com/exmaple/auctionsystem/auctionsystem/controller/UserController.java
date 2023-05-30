package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.UserBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.UserResponseDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.UserUpdateDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.UserMapper;
import com.exmaple.auctionsystem.auctionsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;
  private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

  @GetMapping
  ResponseEntity<List<UserResponseDto>> getAllUsers(){
    List<UserResponseDto> allParticipantsResponse = service.getAllUsers()
      .stream()
      .map(mapper::toResponseDto)
      .toList();

    return new ResponseEntity<>(allParticipantsResponse, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  ResponseEntity<UserBo> getParticipant(@PathVariable Long id){
    UserBo result = service.getUserById(id);
    return ResponseEntity.ok(result);
  }

  @PutMapping("/update/{id}")
  ResponseEntity<UserBo> updateParticipant(@PathVariable Long id, @RequestBody UserUpdateDto body){
    return new ResponseEntity<>(service.updateUser(id, body), HttpStatus.OK);
  }


}
