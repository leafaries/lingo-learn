package com.lingolearn.entities;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Tracks daily statistics and achievements in the language learning
 * application. Each record represents statistics for a single day, enabling
 * tracking of daily progress, streaks, and overall learning metrics.
 */
@Entity
@Table(
        name = "statistics",
        indexes = {
                @Index(name = "idx_statistics_date", columnList = "date", unique = true)
        }
)
public class StatisticsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The date these statistics are for.
     */
    @Column(nullable = false)
    private LocalDate date = LocalDate.now();

    // TODO: Probably delete this column since we don't have marked as a column in WordEntity
    /**
     * Total number of unique words learned (marked as mastered).
     */
    @Column(nullable = false)
    private int totalWordsLearned = 0;

    /**
     * Number of correct answers across all sessions.
     */
    @Column(nullable = false)
    private int correctAnswers = 0;

    /**
     * Total number of answers provided across all sessions.
     */
    @Column(nullable = false)
    private int totalAnswers = 0;

    /**
     * Total time spent studying.
     */
    @Column(nullable = false)
    private Duration totalStudyTime = Duration.ZERO;

    /**
     * Number of completed daily challenges.
     */
    @Column(nullable = false)
    private int dailyChallengesCompleted = 0;

    /**
     * Current streak of consecutive days with activity.
     */
    @Column(nullable = false)
    private int streakDays = 0;

    /**
     * Best streak ever achieved.
     */
    @Column(nullable = false)
    private int bestStreak = 0;

    /**
     * Number of review sessions completed.
     */
    @Column(nullable = false)
    private int reviewSessionsCompleted = 0;

    /**
     * Number of words reviewed in review sessions.
     */
    @Column(nullable = false)
    private int wordsReviewed = 0;

    // Constructors

    public StatisticsEntity() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTotalWordsLearned() {
        return totalWordsLearned;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public Duration getTotalStudyTime() {
        return totalStudyTime;
    }

    public int getDailyChallengesCompleted() {
        return dailyChallengesCompleted;
    }

    public int getStreakDays() {
        return streakDays;
    }

    public int getBestStreak() {
        return bestStreak;
    }

    public int getReviewSessionsCompleted() {
        return reviewSessionsCompleted;
    }

    public int getWordsReviewed() {
        return wordsReviewed;
    }

    // Utility methods

    /**
     * Records a new word learned.
     */
    public void incrementWordsLearned() {
        this.totalWordsLearned++;
    }

    /**
     * Records an answer and whether it was correct.
     */
    public void recordAnswer(boolean correct) {
        this.totalAnswers++;
        if (correct) {
            this.correctAnswers++;
        }
    }

    /**
     * Adds study time to the total.
     */
    public void addStudyTime(Duration duration) {
        this.totalStudyTime = this.totalStudyTime.plus(duration);
    }

    /**
     * Records completion of a daily challenge.
     */
    public void incrementDailyChallenges() {
        this.dailyChallengesCompleted++;
    }

    /**
     * Updates the streak counter.
     */
    public void updateStreak(boolean maintainedStreak) {
        if (maintainedStreak) {
            this.streakDays++;
        } else {
            this.streakDays = 0;
        }
    }

    /**
     * Records completion of a review session.
     */
    public void recordReviewSession(int wordsReviewed) {
        this.reviewSessionsCompleted++;
        this.wordsReviewed += wordsReviewed;
    }

    /**
     * Calculates the accuracy percentage.
     */
    public double getAccuracyPercentage() {
        if (totalAnswers == 0) return 0.0;
        return (double) correctAnswers / totalAnswers * 100.0;
    }

    /**
     * Gets total study time in minutes.
     */
    public long getStudyTimeMinutes() {
        return totalStudyTime.toMinutes();
    }

    /**
     * Resets all statistics for the day.
     */
    public void reset() {
        this.totalWordsLearned = 0;
        this.correctAnswers = 0;
        this.totalAnswers = 0;
        this.totalStudyTime = Duration.ZERO;
        this.dailyChallengesCompleted = 0;
        // Don't reset streaks as they span multiple days
        this.reviewSessionsCompleted = 0;
        this.wordsReviewed = 0;
    }

    /**
     * Checks if there was any learning activity on this day.
     */
    public boolean hasActivity() {
        return totalAnswers > 0 || dailyChallengesCompleted > 0 || !totalStudyTime.isZero();
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatisticsEntity)) return false;
        StatisticsEntity that = (StatisticsEntity) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "StatisticsEntity{" +
                "date=" + date +
                ", wordsLearned=" + totalWordsLearned +
                ", accuracy=" + String.format("%.1f%%", getAccuracyPercentage()) +
                ", studyTime=" + getStudyTimeMinutes() + "min" +
                ", streak=" + streakDays + "days" +
                '}';
    }
}
