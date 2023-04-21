package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.ParticipantBo;
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

  @Override
  public List<ParticipantBo> getAllParticipants(){
    return repository.findAll(Sort.by("id"));
  }

  @Override
  public ParticipantBo getParticipantById(Long id){
    return repository.findById(id).orElseThrow(() -> getNotFoundError(id));
  }

  @Override
  public ParticipantBo createParticipant(ParticipantPostDto dto){
    return repository.save(mapper.toParticipant(dto));
  }

  @Override
  public ParticipantBo updateParticipant(Long id, ParticipantPostDto dto){
    ParticipantBo participant = repository.findById(id).orElseThrow(() -> getNotFoundError(id));
    participant.setName(dto.getName());
    participant.setPersonalIdentification(dto.getPersonalIdentification());

    return repository.save(participant);
  }

  @Override
  public void deleteParticipant(Long id){
     ParticipantBo participant = repository.findById(id).orElseThrow(() -> getNotFoundError(id));
     repository.delete(participant);
  }

  private EntityNotFoundException getNotFoundError(Long id){
    return new EntityNotFoundException(String.format("Participant with id %s doesn't exist", id));
  }


}
