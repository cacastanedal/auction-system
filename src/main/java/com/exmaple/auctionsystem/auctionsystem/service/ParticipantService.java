package com.exmaple.auctionsystem.auctionsystem.service;

import com.exmaple.auctionsystem.auctionsystem.domain.Participant;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantPostDto;

import java.util.List;

public interface ParticipantService {

  List<Participant> getAllParticipants();

  Participant getParticipantById(Long id);

  Participant createParticipant(ParticipantPostDto dto);

  Participant updateParticipant(Long id, ParticipantPostDto dto);

  void deleteParticipant(Long id);

}
