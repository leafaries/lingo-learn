package com.lingolearn.study.flashcard.domain;

import com.lingolearn.vocabulary.word.domain.Word;

public record Flashcard(
        Word word,
        boolean isRevealed,
        Confidence userConfidence
) {
}
