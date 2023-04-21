package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.Participant;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantPostDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.ParticipantMapper;
import com.exmaple.auctionsystem.auctionsystem.persistence.ParticipantRepository;
import com.exmaple.auctionsystem.auctionsystem.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ParticipantServiceImpl implements ParticipantService {

  private final ParticipantRepository repository;
  private final ParticipantMapper mapper = Mappers.getMapper(ParticipantMapper.class);

  public ParticipantServiceImpl(ParticipantRepository repository){
    this.repository = repository;
  }

  public List<Participant> getAllParticipants(){
    return repository.findAll(Sort.by("id"));
  }

  public Participant getParticipantById(Long id){
    return repository.findById(id).orElseThrow(() -> getNotFoundError(id));
  }

  public Participant createParticipant(ParticipantPostDto dto){
    return repository.save(mapper.toParticipant(dto));
  }

  public Participant updateParticipant(Long id, ParticipantPostDto dto){
    Participant participant = repository.findById(id).orElseThrow(() -> getNotFoundError(id));
    participant.setName(dto.getName());
    participant.setPersonalIdentification(dto.getPersonalIdentification());

    return repository.save(participant);
  }

  public void deleteParticipant(Long id){
     Participant participant = repository.findById(id).orElseThrow(() -> getNotFoundError(id));
     repository.delete(participant);
  }

  private EntityNotFoundException getNotFoundError(Long id){
    return new EntityNotFoundException(String.format("Participant with id %s doesn't exist", id));
  }


}
