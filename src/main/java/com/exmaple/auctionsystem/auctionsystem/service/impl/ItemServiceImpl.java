package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.ParticipantBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ItemPostDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.ItemMapper;
import com.exmaple.auctionsystem.auctionsystem.persistence.ItemRepository;
import com.exmaple.auctionsystem.auctionsystem.service.ItemService;
import com.exmaple.auctionsystem.auctionsystem.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;
  private final ParticipantService participantService;
  private final ItemMapper mapper = Mappers.getMapper(ItemMapper.class);

  public ItemBo createItem(ItemPostDto dto){

    ParticipantBo itemOwner = participantService.getParticipantById(dto.getParticipant_id());
    ItemBo itemToCreate = mapper.toItem(dto);
    itemToCreate.setParticipant(itemOwner);

    return itemRepository.save(itemToCreate);
  }
}
