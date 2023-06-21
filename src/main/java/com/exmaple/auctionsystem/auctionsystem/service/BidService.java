package com.exmaple.auctionsystem.auctionsystem.service;

import com.exmaple.auctionsystem.auctionsystem.domain.BidBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.BidPostDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.BidResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BidService {
  Page<BidBo> getAllBidsByPage(Pageable pageDetails);

  BidBo getBidById(Long id);

  BidBo createBid(BidPostDto dto);
}
