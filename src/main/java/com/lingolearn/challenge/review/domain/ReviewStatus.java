package com.lingolearn.challenge.review.domain;

import com.lingolearn.vocabulary.word.domain.Word;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewStatus(
        Long id,
        List<Word> wordsToReview,
        ReviewStatus status,
        LocalDateTime scheduledFor,
        LocalDateTime completedAt
) {
    public boolean isDue() {
        return LocalDateTime.now().isAfter(scheduledFor);
    }
}
