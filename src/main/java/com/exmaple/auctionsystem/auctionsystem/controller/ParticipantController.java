package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.ParticipantBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantPostDto;
import com.exmaple.auctionsystem.auctionsystem.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
public class ParticipantController {

  private final ParticipantService participantService;

  public ParticipantController(ParticipantService service){
    this.participantService = service;
  }

  @GetMapping
  ResponseEntity<List<ParticipantBo>> getAllParticipants(){
    return new ResponseEntity<>(participantService.getAllParticipants(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  ResponseEntity<ParticipantBo> getParticipant(@PathVariable Long id){
    ParticipantBo result = participantService.getParticipantById(id);
    return ResponseEntity.ok(result);
  }

  @PostMapping("/create")
  ResponseEntity<ParticipantBo> createParticipant(@RequestBody ParticipantPostDto body){
    return new ResponseEntity<>(participantService.createParticipant(body), HttpStatus.CREATED);
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

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleException(Exception e){
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }
}
