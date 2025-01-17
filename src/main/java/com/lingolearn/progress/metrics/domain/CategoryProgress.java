package com.lingolearn.progress.metrics.domain;

import java.time.Duration;
import java.time.LocalDateTime;

public record CategoryProgress(
        int totalWords,
        int masteredWords,
        double accuracyRate,
        Duration averageResponseTime,
        LocalDateTime lastStudied) {
}
