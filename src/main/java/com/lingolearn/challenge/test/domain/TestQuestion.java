package com.lingolearn.challenge.test.domain;

import com.lingolearn.vocabulary.word.domain.Word;

import java.util.UUID;

public record TestQuestion(
        UUID id,
        Word word,
        String userAnswer,
        boolean isCorrect
) {
}
