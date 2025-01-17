package com.lingolearn.vocabulary.word.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record Word(
        UUID id,
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
