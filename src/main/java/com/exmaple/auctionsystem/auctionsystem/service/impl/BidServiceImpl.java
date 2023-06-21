package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.BidBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.BidPostDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.BidResponseDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.BidMapper;
import com.exmaple.auctionsystem.auctionsystem.persistence.BidRepository;
import com.exmaple.auctionsystem.auctionsystem.service.AuctionService;
import com.exmaple.auctionsystem.auctionsystem.service.BidService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

  private final BidRepository repository;

  private final AuctionService auctionService;

  @Override
  public Page<BidBo> getAllBidsByPage(Pageable pageDetails){
    return repository.findAll(pageDetails);
  }

  @Override
  public BidBo getBidById(Long id){
    return repository.findById(id)
      .orElseThrow(() -> getNotFoundError(id));
  }

  @Override
  public BidBo createBid(BidPostDto dto){

    BidBo bidBo = BidBo.builder()
      .auction(auctionService.getAuctionById(dto.getAuction_id()))
      .price(dto.getPrice())
      .createdAt(LocalDateTime.now())
      //TODO: Get user from authentication principal
      .build();


    return repository.save(bidBo);
  }

  private EntityNotFoundException getNotFoundError(Long id){
    return new EntityNotFoundException(String.format("Bid with id %s doesn't exist", id));
  }
}
