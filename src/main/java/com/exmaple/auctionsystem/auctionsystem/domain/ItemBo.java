package com.exmaple.auctionsystem.auctionsystem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
public class ItemBo {

  @Id
  //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
  //@SequenceGenerator(name = "item_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "item_user_id")
  private UserBo itemUser;

  @OneToMany(mappedBy = "item")
  private Set<AuctionBo> auctions;

}
