package com.exmaple.auctionsystem.auctionsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bid {
  private @Id
  @GeneratedValue Long id;

  private Double price;

  @OneToOne
  @JoinColumn(name = "participant")
  private Participant participant;

  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "auction_id")
  private Auction auction;
}
