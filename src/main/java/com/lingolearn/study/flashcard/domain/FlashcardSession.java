package com.lingolearn.study.flashcard.domain;

import java.util.List;

public record FlashcardSession(
        Long id,
        Long vocabularySetId,
        List<Flashcard> cards,
        FlashcardMode mode, // WORD_TO_TRANSLATION or TRANSLATION_TO_WORD
        int currentCardIndex
) {
}
