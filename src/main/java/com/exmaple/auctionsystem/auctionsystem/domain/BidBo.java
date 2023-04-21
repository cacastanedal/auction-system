package com.exmaple.auctionsystem.auctionsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;

  private Double price;

  @ManyToOne
  @JoinColumn(name = "participant_id")
  private ParticipantBo participant;

  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "auction_id")
  private AuctionBo auction;
}
