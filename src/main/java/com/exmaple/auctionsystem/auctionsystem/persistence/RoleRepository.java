package com.exmaple.auctionsystem.auctionsystem.persistence;

import com.exmaple.auctionsystem.auctionsystem.domain.Role;
import com.exmaple.auctionsystem.auctionsystem.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(UserRole name);
}
