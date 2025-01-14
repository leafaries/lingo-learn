package com.lingolearn.dtos;

import java.time.Duration;
import java.time.Instant;

public record SessionDTO(
        WordDTO word,
        String userInput,
        boolean correct,
        Duration responseTime,
        Instant timestamp
) {
}
