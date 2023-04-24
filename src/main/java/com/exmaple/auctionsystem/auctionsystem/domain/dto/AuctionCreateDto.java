package com.exmaple.auctionsystem.auctionsystem.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AuctionCreateDto {
  private BigDecimal askingPrice;
  private long item_id;
  private long daysToClose;
}
