package com.lingolearn.challenge.review.domain;

import com.lingolearn.vocabulary.word.domain.Word;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ReviewSession(
        UUID id,
        UUID userId,
        List<Word> wordsToReview,
        ReviewStatus status,
        LocalDateTime scheduledFor,
        LocalDateTime completedAt
) {
    public boolean isDue() {
        return LocalDateTime.now().isAfter(scheduledFor);
    }
}
