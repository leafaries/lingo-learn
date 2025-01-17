package com.lingolearn.progress.metrics.domain;

import java.time.LocalDateTime;

public record StudyProgress(
        int wordsLearned,
        int totalWords,
        double accuracy,
        LocalDateTime lastStudied
) {
}
