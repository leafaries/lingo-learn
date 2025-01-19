package com.lingolearn.vocabulary.category.domain;

import com.lingolearn.study.session.domain.StudyMode;
import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Aggregate statistics for a category, including data from all its vocabulary sets
 * and subcategories.
 */
public record CategoryStatistics(
        int totalSets,
        int totalWords,
        int completedWords,
        int wordsInProgress,
        Map<Difficulty, Integer> difficultyDistribution,
        double averageAccuracy,
        double completionRate,
        LocalDateTime lastActivityDate,
        Map<StudyMode, Integer> sessionsPerMode,
        int totalStudySessions,
        Duration totalStudyTime) {
    public boolean isActive() {
        return lastActivityDate != null &&
                LocalDateTime.now().minusDays(30).isBefore(lastActivityDate);
    }

    public int getUnstartedWords() {
        return totalWords - (completedWords + wordsInProgress);
    }

    public double getAverageSessionDuration() {
        return totalStudySessions == 0 ? 0 :
                totalStudyTime.toMinutes() / (double) totalStudySessions;
    }
}
