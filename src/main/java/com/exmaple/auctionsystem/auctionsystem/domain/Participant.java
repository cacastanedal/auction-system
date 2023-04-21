package com.exmaple.auctionsystem.auctionsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Participant {

  private @Id @GeneratedValue(strategy = SEQUENCE) Long id;
  private String name;
  private String personalIdentification;

  @OneToMany()
  @JoinColumn(name = "item_id")
  private Set<Item> items = new HashSet<>();
}
