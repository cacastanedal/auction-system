package com.exmaple.auctionsystem.auctionsystem.persistence;

import com.exmaple.auctionsystem.auctionsystem.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
