package com.exmaple.auctionsystem.auctionsystem.controller;

import com.exmaple.auctionsystem.auctionsystem.domain.auth.JwtResponse;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.LoginRequest;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.SignupRequest;
import com.exmaple.auctionsystem.auctionsystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService service;

  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
    return ResponseEntity.ok(service.authenticateUser(loginRequest));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){
    service.registerUser(signupRequest);
    return ResponseEntity.ok("new user saved");
  }
}
