package com.exmaple.auctionsystem.auctionsystem.mapper;

import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.UserBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.UserUpdateDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.UserResponseDto;
import com.exmaple.auctionsystem.auctionsystem.persistence.ItemRepository;
import com.exmaple.auctionsystem.auctionsystem.service.impl.ItemServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {ItemServiceImpl.class, ItemRepository.class})
public interface UserMapper {
  UserBo toUserBo(UserUpdateDto updateDto);

  @Mapping(target = "items_id", source = "items")
  UserResponseDto toResponseDto(UserBo userBo);

  default List<Long> itemsToItemsId(List<ItemBo> itemsBo){
    if (itemsBo == null){
      return null;
    }

    return itemsBo.stream()
      .map(ItemBo::getId)
      .toList();
  }
}
