package com.exmaple.auctionsystem.auctionsystem.mapper;

import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.ParticipantBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantPostDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ParticipantResponseDto;
import com.exmaple.auctionsystem.auctionsystem.persistence.ItemRepository;
import com.exmaple.auctionsystem.auctionsystem.service.impl.ItemServiceImpl;
import com.exmaple.auctionsystem.auctionsystem.service.impl.ParticipantServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(uses = {ItemServiceImpl.class, ItemRepository.class})
public interface ParticipantMapper {
  ParticipantBo toParticipant(ParticipantPostDto participantPostDto);

  @Mapping(target = "items_id", source = "items")
  ParticipantResponseDto toResponseDto(ParticipantBo participantBo);

  default List<Long> itemsToItemsId(List<ItemBo> itemsBo){
    if (itemsBo == null){
      return null;
    }

    return itemsBo.stream()
      .map(ItemBo::getId)
      .toList();
  }
}
