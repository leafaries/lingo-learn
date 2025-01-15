package com.lingolearn.dtos.challenge;

import java.time.LocalDate;

public record DailyChallengeDTO(
        SessionDTO session,
        LocalDate date,
        boolean completed,
        int targetWordCount,
        double requiredAccuracy,
        LocalDateTime startTime,
        LocalDateTime completionTime,
        SessionType challengeType
) {

}
