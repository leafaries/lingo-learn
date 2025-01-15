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

    // Default constructor
    public StatisticsEntity() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalWordsLearned() {
        return totalWordsLearned;
    }

    public void setTotalWordsLearned(int totalWordsLearned) {
        this.totalWordsLearned = totalWordsLearned;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public void setTotalAnswers(int totalAnswers) {
        this.totalAnswers = totalAnswers;
    }

    public Duration getTotalStudyTime() {
        return totalStudyTime;
    }

    public void setTotalStudyTime(Duration totalStudyTime) {
        this.totalStudyTime = totalStudyTime;
    }

    public int getDailyChallengesCompleted() {
        return dailyChallengesCompleted;
    }

    public void setDailyChallengesCompleted(int dailyChallengesCompleted) {
        this.dailyChallengesCompleted = dailyChallengesCompleted;
    }

    public int getStreakDays() {
        return streakDays;
    }

    public void setStreakDays(int streakDays) {
        this.streakDays = streakDays;
    }

    // Utility methods
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

    // Calculate accuracy percentage
    public double getAccuracyPercentage() {
        if (totalAnswers == 0) {
            return 0.0;
        }
        return (double) correctAnswers / totalAnswers * 100.0;
    }

    // Reset statistics
    public void reset() {
        this.totalWordsLearned = 0;
        this.correctAnswers = 0;
        this.totalAnswers = 0;
        this.totalStudyTime = Duration.ZERO;
        this.dailyChallengesCompleted = 0;
        this.streakDays = 0;
    }

    @Override
    public String toString() {
        return "StatisticsEntity{" +
                "id=" + id +
                ", date=" + date +
                ", totalWordsLearned=" + totalWordsLearned +
                ", correctAnswers=" + correctAnswers +
                ", totalAnswers=" + totalAnswers +
                ", totalStudyTime=" + totalStudyTime +
                ", dailyChallengesCompleted=" + dailyChallengesCompleted +
                ", streakDays=" + streakDays +
                '}';
    }
}
