package com.exmaple.auctionsystem.auctionsystem.service.impl;

import com.exmaple.auctionsystem.auctionsystem.domain.Role;
import com.exmaple.auctionsystem.auctionsystem.domain.UserBo;
import com.exmaple.auctionsystem.auctionsystem.domain.UserRole;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.JwtResponse;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.LoginRequest;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.SignupRequest;
import com.exmaple.auctionsystem.auctionsystem.domain.auth.UserDetailsImpl;
import com.exmaple.auctionsystem.auctionsystem.jwt.JwtUtils;
import com.exmaple.auctionsystem.auctionsystem.persistence.RoleRepository;
import com.exmaple.auctionsystem.auctionsystem.persistence.UserRepository;
import com.exmaple.auctionsystem.auctionsystem.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final PasswordEncoder encoder;

  private final AuthenticationManager authenticationManager;

  public JwtResponse authenticateUser(LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate( // TODO: Test authException
      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwtToken = JwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).toList();

    return JwtResponse.builder()
      .token(jwtToken)
      .id(userDetails.getId())
      .username(userDetails.getUsername())
      .email(userDetails.getEmail())
      .roles(roles)
      .build();

  }

  public void registerUser(SignupRequest signupRequest){

    if(userRepository.existsByUsername(signupRequest.getUsername())){
      throw new BadCredentialsException("Username already taken");
    }

    if(userRepository.existsByEmail(signupRequest.getEmail())){
      throw new BadCredentialsException("Email already taken");
    }

    Set<Role> userAssignedRoles = new HashSet<>();

    Set<String> signupRequestRoles = signupRequest.getRoles();

    if(signupRequestRoles == null){
      Role roleUser = roleRepository.findByName(UserRole.ROLE_USER).orElseThrow(
        () -> new EntityNotFoundException(String.format("Role %s not found", UserRole.ROLE_USER)));

      userAssignedRoles.add(roleUser);
    } else {
      signupRequestRoles.forEach(singupRole -> {
        switch (singupRole) {
          case "admin":
            Role adminRole = roleRepository.findByName(UserRole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            userAssignedRoles.add(adminRole);

            break;
          case "mod":
            Role modRole = roleRepository.findByName(UserRole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            userAssignedRoles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(UserRole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            userAssignedRoles.add(userRole);
        }
      });
    }

    UserBo userBo = UserBo.builder()
      .username(signupRequest.getUsername())
      .email(signupRequest.getEmail())
      .password(encoder.encode(signupRequest.getPassword()))
      .roles(userAssignedRoles)
      .build();

    userRepository.save(userBo);
  }


}
