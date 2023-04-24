package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.dto.AuctionCreateDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.AuctionResponseDto;
import com.exmaple.auctionsystem.auctionsystem.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionController {

  private final AuctionService service;

  @GetMapping
  public ResponseEntity<List<AuctionResponseDto>> getAllAuctions(){
    return new ResponseEntity<>(service.getAllAuctions(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuctionResponseDto> getAuctionById(@PathVariable Long id){
    return new ResponseEntity<>(service.getAuctionById(id), HttpStatus.OK);

  }

  @PostMapping
  public ResponseEntity<AuctionResponseDto>  createAuction(@RequestBody AuctionCreateDto dto){
    return new ResponseEntity<>(service.createAuction(dto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuctionResponseDto> updateAuction(@PathVariable Long id, @RequestBody AuctionCreateDto dto){
    return new ResponseEntity<>(service.updateAuction(id, dto), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteAuction(@PathVariable Long id){
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

}
