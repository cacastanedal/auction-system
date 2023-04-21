package com.exmaple.auctionsystem.auctionsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "item")
@Getter
@Setter
@RequiredArgsConstructor
public class ItemBo {
  private @Id @GeneratedValue Long id;

  private String name;
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  private ParticipantBo participant;

}
