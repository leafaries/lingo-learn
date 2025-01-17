package com.lingolearn.challenge.test.domain;

import java.time.LocalDateTime;
import java.util.List;

public record VocabularyTest(
        Long id,
        List<Long> vocabularySetIds,
        List<TestQuestion> questions,
        TestStatus status,
        LocalDateTime startedAt,
        LocalDateTime finishedAt,
        double score
) {
    public boolean isInProgress() {
        return status == TestStatus.IN_PROGRESS;
    }
}
