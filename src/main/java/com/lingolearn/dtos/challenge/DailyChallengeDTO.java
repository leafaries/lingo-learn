package com.lingolearn.dtos.challenge;

import com.lingolearn.dtos.SessionDTO;
import com.lingolearn.enums.SessionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
