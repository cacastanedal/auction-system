package com.exmaple.auctionsystem.auctionsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Item {
  private @Id @GeneratedValue Long id;

  private String name;
  private String description;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private Participant participant;

  public Participant getVendor() {
    return participant;
  }

  public void setVendor(Participant participant) {
    this.participant = participant;
  }

}
