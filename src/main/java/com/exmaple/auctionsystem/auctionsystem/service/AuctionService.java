package com.exmaple.auctionsystem.auctionsystem.service;

import com.exmaple.auctionsystem.auctionsystem.domain.AuctionBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.AuctionCreateDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.AuctionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuctionService {

  Page<AuctionBo> getAllAuctionsByPage(Pageable pageDetails);

  AuctionBo getAuctionById(Long id);

  AuctionBo createAuction(AuctionCreateDto dto);

  AuctionBo updateAuction(Long id, AuctionCreateDto dto);

  void deleteAuction(Long id);

}
