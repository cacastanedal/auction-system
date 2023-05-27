package com.exmaple.auctionsystem.auctionsystem.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ParticipantResponseDto {
  private Long id;
  private String name;
  private String personalIdentification;
  private List<Long> items_id;
}
