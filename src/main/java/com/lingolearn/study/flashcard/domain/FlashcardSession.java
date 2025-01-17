package com.lingolearn.study.flashcard.domain;

import java.util.List;
import java.util.UUID;

public record FlashcardSession(
        UUID id,
        UUID userId,
        UUID vocabularySetId,
        List<Flashcard> cards,
        FlashcardMode mode, // WORD_TO_TRANSLATION or TRANSLATION_TO_WORD
        int currentCardIndex
) {
}
