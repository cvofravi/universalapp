package com.codingmould.restproject.dailystandup.standup;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StandupRepository extends JpaRepository<StandupEntry, Long> {
    Optional<StandupEntry> findTopByStandupDateOrderBySubmittedAtDesc(LocalDate date);
    List<StandupEntry> findAllByOrderBySubmittedAtDesc();
}
