package com.lingolearn.study.choice.domain;

import com.lingolearn.study.domain.SessionStatus;

import java.util.List;

public record MultipleChoiceSession(
        Long id,
        Long vocabularySetId,
        List<MultipleChoiceQuestion> questions,
        SessionStatus status,
        int currentQuestionIndex
) {
}