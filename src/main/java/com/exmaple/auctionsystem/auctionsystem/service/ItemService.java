package com.exmaple.auctionsystem.auctionsystem.service;

import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ItemPostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {

  ItemBo createItem(ItemPostDto dto);

  Page<ItemBo> getAllItems(Pageable pageable);

  ItemBo getItem(Long id);

  ItemBo updateItem(Long id, ItemPostDto dto);

  void deleteItem(Long id);
}
