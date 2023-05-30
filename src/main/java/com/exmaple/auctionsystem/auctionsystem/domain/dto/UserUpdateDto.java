package com.exmaple.auctionsystem.auctionsystem.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UserUpdateDto {
  public String nickName;
  public String personalIdentification;
}
