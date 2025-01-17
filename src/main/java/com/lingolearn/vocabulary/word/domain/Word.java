package com.lingolearn.vocabulary.word.domain;

import java.time.LocalDateTime;
import java.util.List;

public record Word(
        Long id,
        String original,
        String translation,
        String partOfSpeech,
        List<String> exampleSentences,
        DifficultyLevel difficulty,
        LocalDateTime lastReviewed,
        int timesReviewed,
        int correctAnswers
) {
    public double getSuccessRate() {
        return timesReviewed == 0 ? 0 : (double) correctAnswers / timesReviewed;
    }
}
