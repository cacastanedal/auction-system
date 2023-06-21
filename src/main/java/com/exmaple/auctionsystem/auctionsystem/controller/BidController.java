package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.dto.BidPostDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.BidResponseDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.BidMapper;
import com.exmaple.auctionsystem.auctionsystem.service.BidService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bid")
@RequiredArgsConstructor
public class BidController {
  private final BidService bidService;

  private final BidMapper mapper = Mappers.getMapper(BidMapper.class);

  @GetMapping
  public ResponseEntity<List<BidResponseDto>> getAllBids(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "20") int size){

    Pageable pageDetails = PageRequest.of(page, size, Sort.by("id"));

    List<BidResponseDto> bidList = bidService.getAllBidsByPage(pageDetails).stream()
      .map(mapper::toResponseDto).toList();

    return new ResponseEntity<>(bidList, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BidResponseDto> getBidById(@PathVariable Long id){
    return new ResponseEntity<>(mapper.toResponseDto(bidService.getBidById(id)), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<BidResponseDto> createBid(@RequestBody BidPostDto dto){
    return new ResponseEntity<>(mapper.toResponseDto(bidService.createBid(dto)), HttpStatus.OK);
  }
}
