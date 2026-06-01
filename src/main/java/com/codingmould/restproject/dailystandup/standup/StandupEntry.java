package com.codingmould.restproject.dailystandup.standup;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class StandupEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate standupDate;
    private LocalDateTime submittedAt;

    @Column(length = 2000)
    private String yesterday;

    @Column(length = 2000)
    private String today;

    @Column(length = 2000)
    private String blockers;

    @PrePersist
    void prePersist() {
        submittedAt = LocalDateTime.now();
        if (standupDate == null) standupDate = LocalDate.now();
    }
}
