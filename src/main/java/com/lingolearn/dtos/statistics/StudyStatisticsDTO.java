package com.lingolearn.dtos.statistics;

import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.core.StatisticsDTO;
import com.lingolearn.enums.Difficulty;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record StudyStatisticsDTO(
        // General statistics
        int totalWordsLearned,
        int totalSessions,
        Duration totalStudyTime,
        double overallAccuracy,

        // Daily progress
        int wordsLearnedToday,
        int sessionsToday,
        Duration studyTimeToday,
        double accuracyToday,

        // Streak information
        int dailyStreak,
        int longestStreak,
        LocalDate lastStudyDate,

        // Challenge statistics
        int dailyChallengesCompleted,
        int totalChallengesCompleted,
        double challengeAccuracy,

        // Category progress
        Map<String, Integer> wordsPerCategory,
        Map<String, Double> accuracyPerCategory,

        // Progress over time
        List<DailyProgress> dailyProgress,

        // Problem areas
        List<ProblemWord> problemWords
) implements StatisticsDTO {
    @Override
    public int getTotalAnswers() {
        // Calculate total answers from daily progress
        return dailyProgress.stream()
                .mapToInt(DailyProgress::totalAnswers)
                .sum();
    }

    @Override
    public int getCorrectAnswers() {
        // Calculate correct answers from daily progress
        return dailyProgress.stream()
                .mapToInt(DailyProgress::correctAnswers)
                .sum();
    }

    @Override
    public double getAccuracy() {
        return overallAccuracy;
    }

    @Override
    public Duration getTotalTime() {
        return totalStudyTime;
    }

    public record DailyProgress(
            LocalDate date,
            int wordsLearned,
            int totalAnswers,
            int correctAnswers,
            Duration studyTime,
            double accuracy,
            boolean challengeCompleted,
            int reviewSessionsCompleted
    ) {
    }

    public record ProblemWord(
            WordDTO word,
            int totalAttempts,
            int failedAttempts,
            double accuracy,
            Duration averageResponseTime,
            Difficulty currentDifficulty,
            String mostCommonMistake,
            LocalDate lastAttempt
    ) {
    }
}
