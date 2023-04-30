package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.ParticipantBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantPostDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantResponseDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.ParticipantMapper;
import com.exmaple.auctionsystem.auctionsystem.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/participant")
@RequiredArgsConstructor
public class ParticipantController {

  private final ParticipantService participantService;
  private final ParticipantMapper mapper = Mappers.getMapper(ParticipantMapper.class);

  @GetMapping
  ResponseEntity<List<ParticipantResponseDto>> getAllParticipants(){
    List<ParticipantResponseDto> allParticipantsResponse = participantService.getAllParticipants()
      .stream()
      .map(mapper::toResponseDto)
      .toList();

    return new ResponseEntity<>(allParticipantsResponse, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  ResponseEntity<ParticipantBo> getParticipant(@PathVariable Long id){
    ParticipantBo result = participantService.getParticipantById(id);
    return ResponseEntity.ok(result);
  }

  @PostMapping
  ResponseEntity<ParticipantResponseDto> createParticipant(@RequestBody ParticipantPostDto body){
    ParticipantResponseDto createdParticipantResponse = mapper.toResponseDto(participantService.createParticipant(body));
    return new ResponseEntity<>(createdParticipantResponse, HttpStatus.CREATED);
  }

  @PutMapping("/update/{id}")
  ResponseEntity<ParticipantBo> updateParticipant(@PathVariable Long id, @RequestBody ParticipantPostDto body){
    return new ResponseEntity<>(participantService.updateParticipant(id, body), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteParticipant(@PathVariable Long id){
    participantService.deleteParticipant(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }


}
