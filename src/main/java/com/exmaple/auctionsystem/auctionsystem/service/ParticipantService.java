package com.exmaple.auctionsystem.auctionsystem.service;

import com.exmaple.auctionsystem.auctionsystem.domain.ParticipantBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantPostDto;

import java.util.List;

public interface ParticipantService {

  List<ParticipantBo> getAllParticipants();

  ParticipantBo getParticipantById(Long id);

  ParticipantBo createParticipant(ParticipantPostDto dto);

  ParticipantBo updateParticipant(Long id, ParticipantPostDto dto);

  void deleteParticipant(Long id);

}
