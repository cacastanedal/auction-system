package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.ItemBo;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ItemPostDto;
import com.exmaple.auctionsystem.auctionsystem.domain.dto.ItemResponseDto;
import com.exmaple.auctionsystem.auctionsystem.mapper.ItemMapper;
import com.exmaple.auctionsystem.auctionsystem.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/item")
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;
  private final ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

  @GetMapping
  ResponseEntity<List<ItemResponseDto>> getAllItems(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "20") int size){
    Pageable pageElements = PageRequest.of(page, size, Sort.by("id"));

    List<ItemResponseDto> itemBoList = itemService.getAllItems(pageElements).stream()
      .map(itemMapper::toResponseDto)
      .collect(Collectors.toList());

    return new ResponseEntity<>(itemBoList, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  ResponseEntity<ItemResponseDto> getItem(@PathVariable Long id){
    return new ResponseEntity<>(itemMapper.toResponseDto(itemService.getItem(id)), HttpStatus.OK);
  }

  @PostMapping("/create")
  ResponseEntity<ItemResponseDto> createItem(@RequestBody ItemPostDto dto){
    return new ResponseEntity<>(itemMapper.toResponseDto(itemService.createItem(dto)), HttpStatus.CREATED);
  }

  @PutMapping("/update/{id}")
  ResponseEntity<ItemResponseDto> updateItem(@PathVariable Long id, @RequestBody ItemPostDto dto){
    return new ResponseEntity<>(itemMapper.toResponseDto(itemService.updateItem(id, dto)), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteItem(@PathVariable Long id){
    itemService.deleteItem(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }


}
