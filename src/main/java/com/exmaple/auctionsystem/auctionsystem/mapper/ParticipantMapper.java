package com.exmaple.auctionsystem.auctionsystem.mapper;

import com.exmaple.auctionsystem.auctionsystem.domain.ParticipantBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParticipantMapper {

  ParticipantMapper INSTANCE = Mappers.getMapper(ParticipantMapper.class);

  ParticipantBo toParticipant(ParticipantPostDto participantPostDto);
}
