package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.Participant;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantPostDto;
import com.exmaple.auctionsystem.auctionsystem.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/participant")
public class ParticipantController {

  private final ParticipantService participantService;

  public ParticipantController(ParticipantService service){
    this.participantService = service;
  }

  @GetMapping("/{id}")
  ResponseEntity<Participant> getParticipant(@PathVariable Long id){
    Participant result = participantService.getParticipantById(id);
    return ResponseEntity.ok(result);
  }

  @PostMapping("/create")
  ResponseEntity<Participant> createParticipant(@RequestBody ParticipantPostDto body){
    return new ResponseEntity<>(participantService.createParticipant(body), HttpStatus.CREATED);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleException(Exception e){
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }
}
