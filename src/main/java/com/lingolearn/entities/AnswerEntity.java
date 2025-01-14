package com.lingolearn.entities;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "answers")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private WordEntity word;

    @Column(nullable = false)
    private String userInput;

    @Column(nullable = false)
    private boolean correct;

    @Column(nullable = false)
    private Duration responseTime;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    // Getters and setters
    public AnswerEntity() {
    }

    public AnswerEntity(SessionEntity session, WordEntity word, String userInput, boolean correct, Duration responseTime) {
        this.session = session;
        this.word = word;
        this.userInput = userInput;
        this.correct = correct;
        this.responseTime = responseTime;
    }
}
