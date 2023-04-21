package com.exmaple.auctionsystem.auctionsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "auction")
@Getter
@Setter
@ToString
public class AuctionBo {
  private @Id @GeneratedValue Long id;

  private BigDecimal askingPrice;

  @OneToOne
  @JoinColumn(name = "item_id")
  private ItemBo item;

  @OneToOne
  @JoinColumn(name = "vendor_id")
  private ParticipantBo vendor;

  private LocalDateTime createdAt;
  private LocalDateTime closesAt;

}
