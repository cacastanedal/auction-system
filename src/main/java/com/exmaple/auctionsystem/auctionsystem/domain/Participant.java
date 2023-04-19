package com.exmaple.auctionsystem.auctionsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Participant {

  private @Id @GeneratedValue Long id;
  private String name;
  private String personalIdentification;

  @OneToMany()
  @JoinColumn(name = "item_id")
  private Set<Item> items = new HashSet<>();
}
