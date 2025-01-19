package com.lingolearn.vocabulary.category.domain;

import com.lingolearn.progress.metrics.domain.StudyProgress;
import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Summary information about a vocabulary set within a category context.
 * Used for displaying set information in category views.
 */
public record VocabularySetInfo(
        Long id,
        String name,
        String description,
        int wordCount,
        Map<Difficulty, Integer> wordsByDifficulty,
        double completionRate,
        LocalDateTime lastStudied,
        StudyProgress progress
) {
    public boolean isCompleted() {
        return completionRate >= 100.0;
    }

    public boolean needsReview() {
        return lastStudied != null &&
                LocalDateTime.now().minusDays(7).isAfter(lastStudied);
    }
}
