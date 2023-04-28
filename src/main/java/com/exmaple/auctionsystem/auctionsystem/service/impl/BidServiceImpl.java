package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.dto.BidResponseDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.BidMapper;
import com.exmaple.auctionsystem.auctionsystem.persistence.BidRepository;
import com.exmaple.auctionsystem.auctionsystem.service.BidService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

  private final BidRepository repository;
  private final BidMapper mapper = Mappers.getMapper(BidMapper.class);

  public List<BidResponseDto> getAllBids(){
    return repository.findAll(Sort.by("id")).stream()
      .map(mapper::toResponseDto)
      .collect(Collectors.toList());
  }

  public BidResponseDto getBidById(Long id){
    return mapper.toResponseDto(repository.findById(id)
      .orElseThrow(() -> getNotFoundError(id)));
  }

  private EntityNotFoundException getNotFoundError(Long id){
    return new EntityNotFoundException(String.format("Bid with id %s doesn't exist", id));
  }
}
