package com.lingolearn.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "daily_challenges")
public class DailyChallengeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    @Column(nullable = false)
    private LocalDate date = LocalDate.now();

    private boolean completed = false;

    // Getters and setters
}
