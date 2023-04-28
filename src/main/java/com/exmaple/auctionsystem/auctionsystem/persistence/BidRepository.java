package com.exmaple.auctionsystem.auctionsystem.persistence;

import com.exmaple.auctionsystem.auctionsystem.domain.BidBo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<BidBo, Long> {
}
