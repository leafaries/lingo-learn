package com.lingolearn.study.choice.domain;

import java.util.UUID;

public record MultipleChoiceSession(
        UUID id,
        UUID vocabularySetId,
        List<MultipleChoiceQuestion> questions,
        SessionStatus status,
        int currentQuestionIndex
) {
}
