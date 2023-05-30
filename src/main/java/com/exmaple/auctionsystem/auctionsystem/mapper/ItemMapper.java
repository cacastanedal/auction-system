package com.exmaple.auctionsystem.auctionsystem.mapper;

import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ItemPostDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ItemResponseDto;
import com.exmaple.auctionsystem.auctionsystem.persistence.UserRepository;
import com.exmaple.auctionsystem.auctionsystem.service.impl.UserServiceImpl;
import org.mapstruct.Mapper;

@Mapper(uses = {UserServiceImpl.class, UserRepository.class})
public interface ItemMapper {

  ItemBo toItem(ItemPostDto itemPostDto);

  ItemResponseDto toResponseDto(ItemBo itemBo);
}
