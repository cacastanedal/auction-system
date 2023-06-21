package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.AuctionBo;
import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.AuctionCreateDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.AuctionResponseDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.AuctionMapper;
import com.exmaple.auctionsystem.auctionsystem.persistence.AuctionRepository;
import com.exmaple.auctionsystem.auctionsystem.service.AuctionService;
import com.exmaple.auctionsystem.auctionsystem.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

  private final AuctionRepository auctionRepository;
  private final ItemService itemService;
  private final AuctionMapper mapper = Mappers.getMapper(AuctionMapper.class);

  @Override
  public Page<AuctionBo> getAllAuctionsByPage(Pageable pageDetails){
    return auctionRepository.findAll(pageDetails);
  }

  @Override
  public AuctionBo getAuctionById(Long id){
    return auctionRepository.findById(id).orElseThrow(() -> getNotFoundError(id));
  }

  @Override
  public AuctionBo createAuction(AuctionCreateDto dto){
    ItemBo itemBo = itemService.getItem(dto.getItem_id());

    AuctionBo auctionBo = mapper.toBo(dto);

    auctionBo.setItem(itemBo);
    auctionBo.setCreatedAt(LocalDateTime.now());
    auctionBo.setUpdatedAt(LocalDateTime.now());
    auctionBo.setClosesAt(LocalDateTime.now().plusDays(dto.getDaysToClose()));

    return auctionRepository.save(auctionBo);
  }

  @Override
  public AuctionBo updateAuction(Long id, AuctionCreateDto dto){
    AuctionBo auctionBo = auctionRepository.findById(id).orElseThrow(() -> getNotFoundError(id));
    ItemBo itemBo = itemService.getItem(dto.getItem_id());

    auctionBo.setAskingPrice(dto.getAskingPrice());
    auctionBo.setItem(itemBo);

    auctionBo.setUpdatedAt(LocalDateTime.now());
    auctionBo.setClosesAt(LocalDateTime.now().plusDays(dto.getDaysToClose()));

    return auctionRepository.save(auctionBo);
  }

  @Override
  public void deleteAuction(Long id){
    AuctionBo auctionBo = auctionRepository.findById(id).orElseThrow(() -> getNotFoundError(id));
    auctionRepository.delete(auctionBo);
  }

  private EntityNotFoundException getNotFoundError(Long id){
    return new EntityNotFoundException(String.format("Auction with id %s doesn't exist", id));
  }

}
