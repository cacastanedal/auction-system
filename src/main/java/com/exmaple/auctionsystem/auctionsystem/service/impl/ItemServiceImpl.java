package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.ParticipantBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ItemPostDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.ItemMapper;
import com.exmaple.auctionsystem.auctionsystem.persistence.ItemRepository;
import com.exmaple.auctionsystem.auctionsystem.service.ItemService;
import com.exmaple.auctionsystem.auctionsystem.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;
  private final ParticipantService participantService;
  private final ItemMapper mapper = Mappers.getMapper(ItemMapper.class);

  @Override
  public ItemBo createItem(ItemPostDto dto){

    ParticipantBo itemOwner = participantService.getParticipantById(dto.getParticipant_id());
    ItemBo itemToCreate = mapper.toItem(dto);
    itemToCreate.setParticipant(itemOwner);

    return itemRepository.save(itemToCreate);
  }

  @Override
  public List<ItemBo> getAllItems(){
    return itemRepository.findAll(Sort.by("id"));
  }

  @Override
  public ItemBo getItem(Long id){
    return itemRepository.findById(id).orElseThrow(() -> getNotFoundError(id));
  }

  @Override
  public ItemBo updateItem(Long id, ItemPostDto dto) {
    ItemBo item = itemRepository.findById(id).orElseThrow(() -> getNotFoundError(id));
    item.setName(dto.getName());
    item.setDescription(dto.getDescription());

    return itemRepository.save(item);
  }

  @Override
  public void deleteItem(Long id) {
    ItemBo item = itemRepository.findById(id).orElseThrow(() -> getNotFoundError(id));

    itemRepository.delete(item);
  }

  private EntityNotFoundException getNotFoundError(Long id){
    return new EntityNotFoundException(String.format("Item with id %s doesn't exist", id));
  }

}
