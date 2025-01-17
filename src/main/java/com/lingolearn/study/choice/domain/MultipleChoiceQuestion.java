package com.lingolearn.study.choice.domain;

import com.lingolearn.vocabulary.word.domain.Word;

import java.util.List;
import java.util.UUID;

public record MultipleChoiceQuestion(
        Word correctWord,
        List<Word> options,
        UUID userAnswerId,
        boolean isAnsweredCorrectly
) {
}
