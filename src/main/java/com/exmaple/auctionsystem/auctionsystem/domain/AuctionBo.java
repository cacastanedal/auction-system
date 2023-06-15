package com.exmaple.auctionsystem.auctionsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "auction")
@Getter
@Setter
@RequiredArgsConstructor
public class AuctionBo {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auction_seq")
  @SequenceGenerator(name = "auction_seq", allocationSize = 1)
  private Long id;

  private BigDecimal askingPrice;

  @ManyToOne(fetch = FetchType.LAZY)
  private ItemBo item;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime closesAt;

}
