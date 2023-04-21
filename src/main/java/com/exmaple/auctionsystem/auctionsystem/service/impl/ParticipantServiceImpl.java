package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.Participant;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantPostDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.ParticipantMapper;
import com.exmaple.auctionsystem.auctionsystem.persistence.ParticipantRepository;
import com.exmaple.auctionsystem.auctionsystem.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class ParticipantServiceImpl implements ParticipantService {

  private final ParticipantRepository repository;
  private final ParticipantMapper mapper = Mappers.getMapper(ParticipantMapper.class);

  public ParticipantServiceImpl(ParticipantRepository repository){
    this.repository = repository;
  }

  public Participant getParticipantById(Long id){
    return repository.findById(id).orElseThrow(() ->
      new EntityNotFoundException(String.format("Participant with id %s doesn't exist", id)));
  }

  public Participant createParticipant(ParticipantPostDto dto){
    return repository.save(mapper.toParticipant(dto));
  }


}
