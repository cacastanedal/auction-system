package com.exmaple.auctionsystem.auctionsystem.mapper;

import com.exmaple.auctionsystem.auctionsystem.domain.AuctionBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.AuctionCreateDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.AuctionResponseDto;
import com.exmaple.auctionsystem.auctionsystem.persistence.ItemRepository;
import com.exmaple.auctionsystem.auctionsystem.service.impl.ItemServiceImpl;
import org.mapstruct.Mapper;

@Mapper(uses = {ItemServiceImpl.class, ItemRepository.class})
public interface AuctionMapper {
  AuctionBo toBo(AuctionCreateDto dto);

  // TODO: map item as item_id
  AuctionResponseDto toResponseDto(AuctionBo bo);
}
