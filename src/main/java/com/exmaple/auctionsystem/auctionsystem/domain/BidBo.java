package com.exmaple.auctionsystem.auctionsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bid")
@Getter
@Setter
@ToString
public class BidBo {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bid_seq")
  @SequenceGenerator(name = "bid_seq", allocationSize = 1)
  private Long id;

  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserBo user;

  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "auction_id")
  private AuctionBo auction;
}
