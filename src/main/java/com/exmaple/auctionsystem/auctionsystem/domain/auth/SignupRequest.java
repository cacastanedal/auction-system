package com.exmaple.auctionsystem.auctionsystem.domain.auth;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SignupRequest {
  private String username;
  private String password;
  private String email;
  private Set<String> roles;
}
