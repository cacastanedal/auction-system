package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.ParticipantBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantResponseDto;
import com.exmaple.auctionsystem.auctionsystem.service.ParticipantService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
public class ParticipantControllerTest {

  @Autowired
  private ParticipantController controller;

  @MockBean
  private ParticipantService service;


  @Test
  void getAllParticipants(){

    List<ParticipantBo> allParticipants = List.of(ParticipantBo.builder().id(1L).build(),
      ParticipantBo.builder().id(2L).build()
    );

    when(service.getAllParticipants()).thenReturn(allParticipants);

    ResponseEntity<List<ParticipantResponseDto>> response = controller.getAllParticipants();

    Assertions.assertEquals(response.getStatusCode().value(), 200);

  }

}
