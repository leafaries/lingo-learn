package com.lingolearn.study.manual.domain;

import java.util.List;

public record ManualInputSession(
        Long id,
        Long vocabularySetId,
        List<ManualInputQuestion> questions,
        int currentQuestionIndex
) {
}
