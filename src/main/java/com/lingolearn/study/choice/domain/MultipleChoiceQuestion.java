package com.lingolearn.study.choice.domain;

import com.lingolearn.vocabulary.word.domain.Word;

import java.util.List;

public record MultipleChoiceQuestion(
        Word correctWord,
        List<Word> options,
        Long userAnswerId,
        boolean isAnsweredCorrectly
) {
}
