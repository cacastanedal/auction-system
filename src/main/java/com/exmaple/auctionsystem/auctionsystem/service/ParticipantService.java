package com.exmaple.auctionsystem.auctionsystem.service;

import com.exmaple.auctionsystem.auctionsystem.domain.Participant;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantPostDto;

public interface ParticipantService {

  Participant getParticipantById(Long id);

  Participant createParticipant(ParticipantPostDto dto);

}
