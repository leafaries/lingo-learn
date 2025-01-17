package com.lingolearn.challenge.test.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record VocabularyTest(
        UUID id,
        List<UUID> vocabularySetIds,
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
