package com.lingolearn.entities;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;

@Entity
@Table(name = "statistics")
public class StatisticsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date = LocalDate.now();

    @Column(nullable = false)
    private int totalWordsLearned = 0;

    @Column(nullable = false)
    private int correctAnswers = 0;

    @Column(nullable = false)
    private int totalAnswers = 0;

    @Column(nullable = false)
    private Duration totalStudyTime = Duration.ZERO;

    @Column(nullable = false)
    private int dailyChallengesCompleted = 0;

    @Column(nullable = false)
    private int streakDays = 0;

    // Getters and setters
    public void incrementWordsLearned() {
        this.totalWordsLearned++;
    }

    public void recordAnswer(boolean correct) {
        this.totalAnswers++;
        if (correct) {
            this.correctAnswers++;
        }
    }

    public void addStudyTime(Duration duration) {
        this.totalStudyTime = this.totalStudyTime.plus(duration);
    }

    public void incrementDailyChallenges() {
        this.dailyChallengesCompleted++;
    }

    public void updateStreak(boolean maintainedStreak) {
        if (maintainedStreak) {
            this.streakDays++;
        } else {
            this.streakDays = 0;
        }
    }
}
