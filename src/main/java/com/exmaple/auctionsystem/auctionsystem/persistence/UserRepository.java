package com.exmaple.auctionsystem.auctionsystem.persistence;

import com.exmaple.auctionsystem.auctionsystem.domain.UserBo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserBo, Long> {
  Optional<UserBo> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
