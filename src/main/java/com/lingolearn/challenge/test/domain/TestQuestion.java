package com.lingolearn.challenge.test.domain;

import com.lingolearn.vocabulary.word.domain.Word;

public record TestQuestion(
        Long id,
        Word word,
        String userAnswer,
        boolean isCorrect
) {
}
