package com.lingolearn.vocabulary.word.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public record Word(
        Long id,
        String original,
        String translation,
        String partOfSpeech,
        List<String> exampleSentences,
        Difficulty difficulty,
        LocalDateTime lastReviewed,
        int timesReviewed,
        int correctAnswers
) {
    public Word {
        if (original == null || original.isBlank()) {
            throw new IllegalArgumentException("Original word cannot be empty;");
        }
        if (translation == null || translation.isBlank()) {
            throw new IllegalArgumentException("Translation cannot be empty;");
        }
    }

    public double getSuccessRate() {
        return timesReviewed == 0 ? 0 : (double) correctAnswers / timesReviewed;
    }

    public Word recordAttempt(boolean wasCorrect) {
        return new Word(
                id,
                original,
                translation,
                partOfSpeech,
                exampleSentences,
                difficulty,
                LocalDateTime.now(),
                timesReviewed + 1,
                correctAnswers + (wasCorrect ? 1 : 0)
        );
    }

    public Word updateDifficulty(Difficulty newDifficulty) {
        return new Word(
                id,
                original,
                translation,
                partOfSpeech,
                exampleSentences,
                newDifficulty,
                lastReviewed,
                timesReviewed,
                correctAnswers
        );
    }

    public boolean needsReview() {
        if (getSuccessRate() < 0.7) return true;
        if (lastReviewed == null) return true;
        return ChronoUnit.DAYS.between(lastReviewed, LocalDateTime.now()) > 7;
    }
}
