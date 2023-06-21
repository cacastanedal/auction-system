package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.UserBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.UserResponseDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.UserMapper;
import com.exmaple.auctionsystem.auctionsystem.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

  private UserController userController;

  @Mock
  private UserService service;

  @Mock
  private UserMapper mapper;

  @BeforeEach
  public void setup(){
    userController = new UserController(service);

  }


  @Test
  void getAllParticipants(){

    Page<UserBo> allParticipants = new PageImpl<UserBo>(List.of(UserBo.builder().id(1L).build(),
      UserBo.builder().id(2L).build()
    ));

    Pageable pageDetails = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));

    when(service.getAllUsersByPage(pageDetails)).thenReturn(allParticipants);

    ResponseEntity<List<UserResponseDto>> response = userController.getAllUsers(0,20);

    Assertions.assertEquals(response.getStatusCode().value(), 200);

  }

  @Test
  void getParticipantById(){
    long userId = 3L;
    UserBo user = UserBo.builder()
      .id(userId).build();

    when(service.getUserById(userId)).thenReturn(user);

    ResponseEntity<UserBo> response = userController.getParticipant(userId);

    assertThat(response.getStatusCode().value()).isEqualTo(200);
    assertThat(response.getBody().getId()).isEqualTo(userId);
  }

}
