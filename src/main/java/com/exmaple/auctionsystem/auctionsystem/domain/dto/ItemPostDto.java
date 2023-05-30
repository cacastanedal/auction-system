package com.exmaple.auctionsystem.auctionsystem.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemPostDto {
  private String name;
  private String description;
  private Long user_id;
}
