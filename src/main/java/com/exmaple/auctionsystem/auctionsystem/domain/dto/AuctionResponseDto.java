package com.exmaple.auctionsystem.auctionsystem.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class AuctionResponseDto {
  private Long id;
  private BigDecimal askingPrice;
  private Long item_id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime closesAt;
}
