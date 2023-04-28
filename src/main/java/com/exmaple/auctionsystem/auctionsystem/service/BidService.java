package com.exmaple.auctionsystem.auctionsystem.service;

import com.exmaple.auctionsystem.auctionsystem.domain.dto.BidResponseDto;

import java.util.List;


public interface BidService {
  List<BidResponseDto> getAllBids();

  BidResponseDto getBidById(Long id);
}
