package com.exmaple.auctionsystem.auctionsystem.mapper;

import com.exmaple.auctionsystem.auctionsystem.domain.BidBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.BidResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface BidMapper {

  BidBo toBo(BidResponseDto dto);

  BidResponseDto toResponseDto(BidBo bo);
}
