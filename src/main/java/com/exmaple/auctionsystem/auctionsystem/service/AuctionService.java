package com.exmaple.auctionsystem.auctionsystem.service;

import com.exmaple.auctionsystem.auctionsystem.domain.dto.AuctionCreateDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.AuctionResponseDto;

import java.util.List;

public interface AuctionService {

  List<AuctionResponseDto> getAllAuctions();

  AuctionResponseDto getAuctionById(Long id);

  AuctionResponseDto createAuction(AuctionCreateDto dto);

  AuctionResponseDto updateAuction(Long id, AuctionCreateDto dto);

  void deleteAuction(Long id);

}
