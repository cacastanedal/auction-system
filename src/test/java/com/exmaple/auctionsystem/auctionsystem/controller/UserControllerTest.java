package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.UserBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.UserResponseDto;
import com.exmaple.auctionsystem.auctionsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
public class UserControllerTest {

  @Autowired
  private UserController controller;

  @MockBean
  private UserService service;


  @Test
  void getAllParticipants(){

    List<UserBo> allParticipants = List.of(UserBo.builder().id(1L).build(),
      UserBo.builder().id(2L).build()
    );

    when(service.getAllUsers()).thenReturn(allParticipants);

    ResponseEntity<List<UserResponseDto>> response = controller.getAllUsers();

    Assertions.assertEquals(response.getStatusCode().value(), 200);

  }

  @Test
  void getParticipantById(){
    long userId = 3L;
    UserBo user = UserBo.builder()
      .id(userId).build();

    when(service.getUserById(userId)).thenReturn(user);

    ResponseEntity<UserBo> response = controller.getParticipant(userId);

    assertThat(response.getStatusCode().value()).isEqualTo(200);
    assertThat(response.getBody().getId()).isEqualTo(userId);
  }

}
