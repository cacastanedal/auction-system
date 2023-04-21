package com.exmaple.auctionsystem.auctionsystem.mapper;

import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ItemPostDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ItemResponseDto;
import com.exmaple.auctionsystem.auctionsystem.persistence.ParticipantRepository;
import com.exmaple.auctionsystem.auctionsystem.service.impl.ParticipantServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ParticipantServiceImpl.class, ParticipantRepository.class})
public interface ItemMapper {

  ItemBo toItem(ItemPostDto itemPostDto);

  ItemResponseDto toResponseDto(ItemBo itemBo);
}
