package com.exmaple.auctionsystem.auctionsystem.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserResponseDto {
  private Long id;
  private String username;
  private String nickName;
  private String personalIdentification;
  private List<Long> items_id;
}
