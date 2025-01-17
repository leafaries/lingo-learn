package com.lingolearn.study.flashcard.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public record FlashcardSession(
        Long id,
        Long vocabularySetId,
        List<Flashcard> cards,
        FlashcardMode mode, // WORD_TO_TRANSLATION or TRANSLATION_TO_WORD
        int currentCardIndex,
        Duration timeSpent,
        LocalDateTime startedAt
) {
    public boolean isCompleted() {
        return currentCardIndex >= cards.size();
    }

    public int getRemainingCards() {
        return cards.size() - currentCardIndex;
    }
}
