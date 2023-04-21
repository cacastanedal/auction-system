package com.exmaple.auctionsystem.auctionsystem.service;

import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ItemPostDto;

import java.util.List;

public interface ItemService {

  ItemBo createItem(ItemPostDto dto);

  List<ItemBo> getAllItems();

  ItemBo getItem(Long id);

  ItemBo updateItem(Long id, ItemPostDto dto);

  void deleteItem(Long id);
}
