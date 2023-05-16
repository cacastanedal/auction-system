package com.exmaple.auctionsystem.auctionsystem.service;

import com.exmaple.auctionsystem.auctionsystem.domain.auth.JwtResponse;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.LoginRequest;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.SignupRequest;

public interface AuthService {
  JwtResponse authenticateUser(LoginRequest loginRequest);

  void registerUser(SignupRequest signupRequest);
}
