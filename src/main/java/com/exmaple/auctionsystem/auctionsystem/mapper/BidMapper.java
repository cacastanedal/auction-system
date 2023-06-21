package com.exmaple.auctionsystem.auctionsystem.mapper;

import com.exmaple.auctionsystem.auctionsystem.domain.BidBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.BidPostDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.BidResponseDto;
import com.exmaple.auctionsystem.auctionsystem.persistence.AuctionRepository;
import com.exmaple.auctionsystem.auctionsystem.service.impl.AuctionServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {AuctionServiceImpl.class, AuctionRepository.class})
public interface BidMapper {

  BidBo toBo(BidResponseDto dto);

  @Mapping(target = "auction_id", source = "auction.id")
  BidResponseDto toResponseDto(BidBo bo);

}
