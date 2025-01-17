package com.lingolearn.study.srs.domain;

import com.lingolearn.study.flashcard.domain.Confidence;
import com.lingolearn.vocabulary.word.domain.Word;

import java.time.LocalDateTime;

public record StudyCard(
        Long id,
        Word word,
        int repetitionLevel,
        LocalDateTime nextReviewDate,
        double easeFactor,
        List<ReviewHistory> history
) {
    public StudyCard updateSpacing(boolean wasCorrect, Confidence confidence) {
        // TODO: Impl
        // SRS algorithm impl
    }
}
