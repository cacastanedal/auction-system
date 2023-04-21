package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ItemPostDto;
import com.exmaple.auctionsystem.auctionsystem.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/item")
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @PostMapping("/create")
  public ResponseEntity<ItemBo> createItem(@RequestBody ItemPostDto dto){
    return new ResponseEntity<>(itemService.createItem(dto), HttpStatus.CREATED);
  }

}
