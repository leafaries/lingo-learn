package com.lingolearn.dtos.statistics;

import com.lingolearn.dtos.core.StatisticsDTO;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;

/*
 - Generated on demand
 - Represent agreggated data
 - Not stored or referenced directly
 */
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
        return totalAnswers;
    }

    @Override
    public int getCorrectAnswers() {
        return correctAnswers;
    }

    @Override
    public double getAccuracy() {
        return accuracy;
    }

    .Override
    public Duration getTotalTime() {
        return studyTime;
    }
}
