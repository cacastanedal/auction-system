package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.UserBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ItemPostDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.ItemMapper;
import com.exmaple.auctionsystem.auctionsystem.persistence.ItemRepository;
import com.exmaple.auctionsystem.auctionsystem.service.ItemService;
import com.exmaple.auctionsystem.auctionsystem.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;
  private final UserService userService;
  private final ItemMapper mapper = Mappers.getMapper(ItemMapper.class);

  @Override
  public ItemBo createItem(ItemPostDto dto){

    UserBo itemOwner = userService.getUserById(dto.getUser_id());
    ItemBo itemToCreate = mapper.toItem(dto);
    itemToCreate.setUser(itemOwner);

    return itemRepository.save(itemToCreate);
  }

  @Override
  public Page<ItemBo> getAllItems(Pageable pageable){
    return itemRepository.findAll(pageable);
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
