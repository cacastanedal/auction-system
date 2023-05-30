package com.exmaple.auctionsystem.auctionsystem.jwt;

import com.exmaple.auctionsystem.auctionsystem.domain.auth.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.util.Date;

@UtilityClass
@Slf4j
public class JwtUtils {

  private final String JWT_SECRET = "jwt.secret";
  private final int JWT_EXPIRATION_MS = 86400000;

  public String generateJwtToken(Authentication authentication){
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    return Jwts.builder()
      .setSubject(userPrincipal.getUsername())
      .setIssuer("Application")
      .setIssuedAt(new Date())
      .setExpiration(new Date((new Date()).getTime() + JWT_EXPIRATION_MS))
      .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
      .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      log.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      log.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      log.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
