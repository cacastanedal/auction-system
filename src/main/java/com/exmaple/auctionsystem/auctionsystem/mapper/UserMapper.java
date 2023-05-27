package com.exmaple.auctionsystem.auctionsystem.mapper;

import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.ParticipantBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.UserUpdateDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.UserResponseDto;
import com.exmaple.auctionsystem.auctionsystem.persistence.ItemRepository;
import com.exmaple.auctionsystem.auctionsystem.service.impl.ItemServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {ItemServiceImpl.class, ItemRepository.class})
public interface ParticipantMapper {
  ParticipantBo toParticipant(UserUpdateDto participantPostDto);

  @Mapping(target = "items_id", source = "items")
  UserResponseDto toResponseDto(ParticipantBo participantBo);

  default List<Long> itemsToItemsId(List<ItemBo> itemsBo){
    if (itemsBo == null){
      return null;
    }

    return itemsBo.stream()
      .map(ItemBo::getId)
      .toList();
  }
}
