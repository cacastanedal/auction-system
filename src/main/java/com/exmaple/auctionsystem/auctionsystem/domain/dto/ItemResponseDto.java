package com.exmaple.auctionsystem.auctionsystem.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponseDto {
  private Long id;
  private String name;
  private String description;
}
