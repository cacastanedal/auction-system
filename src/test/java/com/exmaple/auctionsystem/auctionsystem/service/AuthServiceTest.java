package com.exmaple.auctionsystem.auctionsystem.service;

import com.exmaple.auctionsystem.auctionsystem.domain.Role;
import com.exmaple.auctionsystem.auctionsystem.domain.User;
import com.exmaple.auctionsystem.auctionsystem.domain.UserRole;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.JwtResponse;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.LoginRequest;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.SignupRequest;
import com.exmaple.auctionsystem.auctionsystem.persistence.RoleRepository;
import com.exmaple.auctionsystem.auctionsystem.persistence.UserRepository;
import com.exmaple.auctionsystem.auctionsystem.service.impl.AuthServiceImpl;
import com.exmaple.auctionsystem.auctionsystem.service.impl.UserDetailsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private RoleRepository roleRepository;

  @Mock
  private PasswordEncoder encoder;

  @Mock
  private AuthenticationManager authenticationManager;

  private AuthServiceImpl authService;
  @BeforeEach
  public void setup(){
    authService = new AuthServiceImpl(userRepository, roleRepository, encoder, authenticationManager);
  }

  @Test
  public void authenticationShouldReturnUserInfo(){
    UserDetailsImpl loggedUser = UserDetailsImpl.builder()
      .id(101L)
      .username("test")
      .email("test@test.com")
      .authorities(Collections.emptyList())
      .build();

    Authentication testAuth = new UsernamePasswordAuthenticationToken(loggedUser, "test");
    LoginRequest testRequest = LoginRequest.builder()
      .username("test")
      .password("test")
      .build();

    when(authenticationManager.authenticate(any())).thenReturn(testAuth);

    JwtResponse response = authService.authenticateUser(testRequest);

    assertThat(response).isNotNull();

    assertThat(response.getId()).isEqualTo(loggedUser.getId());
    assertThat(response.getUsername()).isEqualTo(loggedUser.getUsername());
    assertThat(response.getEmail()).isEqualTo(loggedUser.getEmail());
  }

  @Test
  public void registrationShouldReturnUserInfo(){
    SignupRequest request = SignupRequest.builder()
      .username("testUser")
      .email("test@test.com")
      .password("testUser")
      .build();

    Role testRole = Role.builder().id(1).name(UserRole.ROLE_USER).build();

    when(roleRepository.findByName(UserRole.ROLE_USER))
      .thenReturn(Optional.of(testRole));

    authService.registerUser(request);

    verify(userRepository).save(any(User.class)); //TODO: Improve validation for saved user
  }
}
