package com.lingolearn.study.srs.domain;

import com.lingolearn.study.flashcard.domain.Confidence;

import java.time.Duration;
import java.time.LocalDateTime;

public record ReviewHistory(
        Long id,
        LocalDateTime reviewedAt,
        boolean wasCorrect,
        Confidence confidence,
        Duration responseTime
) {
}
