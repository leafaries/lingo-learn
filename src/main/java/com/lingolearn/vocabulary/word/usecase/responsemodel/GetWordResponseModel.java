package com.lingolearn.vocabulary.word.usecase.responsemodel;

import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.time.LocalDateTime;
import java.util.List;

public record GetWordResponseModel(
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
}
