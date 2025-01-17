package com.lingolearn.study.manual.domain;

import java.util.UUID;

public record ManualInputSession(
        UUID id,
        UUID vocabularySetId,
        List<ManualInputQuestion> questions,
        int currentQuestionIndex
) {
}
