package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.dto.AuctionCreateDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.AuctionResponseDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.AuctionMapper;
import com.exmaple.auctionsystem.auctionsystem.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionController {

  private final AuctionService service;

  private final AuctionMapper mapper = Mappers.getMapper(AuctionMapper.class);

  @GetMapping
  public ResponseEntity<List<AuctionResponseDto>> getAllAuctionsByPage(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "20") int size){

    Pageable pageDetails = PageRequest.of(page, size, Sort.by("id"));

    List<AuctionResponseDto> auctionList = service.getAllAuctionsByPage(pageDetails).stream()
      .map(mapper::toResponseDto)
      .toList();

    return new ResponseEntity<>(auctionList, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuctionResponseDto> getAuctionById(@PathVariable Long id){
    return new ResponseEntity<>(mapper.toResponseDto(service.getAuctionById(id)), HttpStatus.OK);

  }

  @PostMapping
  public ResponseEntity<AuctionResponseDto>  createAuction(@RequestBody AuctionCreateDto dto){
    return new ResponseEntity<>(mapper.toResponseDto(service.createAuction(dto)), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuctionResponseDto> updateAuction(@PathVariable Long id, @RequestBody AuctionCreateDto dto){
    return new ResponseEntity<>(mapper.toResponseDto(service.updateAuction(id, dto)), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteAuction(@PathVariable Long id){
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

}
