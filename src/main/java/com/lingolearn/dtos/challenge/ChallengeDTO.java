package com.lingolearn.dtos.challenge;

import com.lingolearn.dtos.StudyResultDTO;

import java.time.LocalDate;

public record ChallengeDTO(
        LocalDate date,
        boolean completed,
        StudyResultDTO result,
        int streakDays
) {

}
