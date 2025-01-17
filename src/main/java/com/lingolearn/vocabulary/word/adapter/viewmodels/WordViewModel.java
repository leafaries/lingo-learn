package com.lingolearn.vocabulary.word.adapter.viewmodels;

import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.time.LocalDateTime;
import java.util.List;

public record WordViewModel(
        Long id,
        String original,
        String translation,
        String partOfSpeech,
        List<String> exampleSentences,
        Difficulty difficulty,
        LocalDateTime lastReviewed,
        int timesReviewed,
        int correctAnswers,
        double successRate,
        // View-specific fields
        String difficultyLabel,
        String lastReviewedFormatted,
        String successRateFormatted
) {
}
