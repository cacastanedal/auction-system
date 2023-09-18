package com.exmaple.auctionsystem.auctionsystem.persistence;

import com.exmaple.auctionsystem.auctionsystem.domain.UserBo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserBo, Long> {
  Optional<UserBo> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query("SELECT u FROM UserBo u JOIN u.items i GROUP BY u.id ORDER BY COUNT(i) DESC")
  List<UserBo> findUserWithMostItems(Pageable pageable);
}
