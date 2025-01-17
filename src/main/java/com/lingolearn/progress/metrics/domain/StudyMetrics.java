package com.lingolearn.progress.metrics.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;

public record StudyMetrics(
        Duration totalStudyTime,
        int totalWordsLearned,
        int totalSessions,
        Map<LocalDate, Integer> dailyProgress,
        double averageAccuracy
) {
}
