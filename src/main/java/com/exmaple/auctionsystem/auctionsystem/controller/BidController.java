package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.dto.BidResponseDto;
import com.exmaple.auctionsystem.auctionsystem.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bid")
@RequiredArgsConstructor
public class BidController {
  private final BidService bidService;

  @GetMapping
  public ResponseEntity<List<BidResponseDto>> getAllBids(){
    return new ResponseEntity<>(bidService.getAllBids(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BidResponseDto> getBidById(@PathVariable Long id){
    return new ResponseEntity<>(bidService.getBidById(id), HttpStatus.OK);
  }
}
