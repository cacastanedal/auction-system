package com.exmaple.auctionsystem.auctionsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Auction {
  private @Id @GeneratedValue Long id;

  private BigDecimal askingPrice;

  @OneToOne
  @JoinColumn(name = "item_id")
  private Item item;

  @OneToOne
  @JoinColumn(name = "vendor_id")
  private Participant vendor;

  @OneToMany
  private Set<Bid> bids;

  private LocalDateTime createdAt;
  private LocalDateTime closesAt;

}
