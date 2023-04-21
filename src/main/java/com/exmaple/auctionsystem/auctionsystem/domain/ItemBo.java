package com.exmaple.auctionsystem.auctionsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ItemBo {
  private @Id @GeneratedValue Long id;

  private String name;
  private String description;

  @ManyToOne
  @JoinColumn(name = "participant_id")
  private ParticipantBo participant;

}
