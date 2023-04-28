package com.exmaple.auctionsystem.auctionsystem.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class BidResponseDto {
  private Long id;
  private BigDecimal price;
  private Long participant_id;
  private LocalDateTime createdAt;
  private Long auction_id;
}
