package com.lingolearn.dtos;

import com.lingolearn.dtos.study.AnswerDTO;
import com.lingolearn.enums.SessionState;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public record SessionDTO(
        VocabularySetDTO vocabularySet,
        StudyMode mode,
        SessionType type,
        SessionState state,
        Instant startTime,
        Instant endTime,
        List<AnswerDTO> answers,
        int correctAnswers,
        int totalAnswers,
        int currentStreak,
        int bestStreak
) {
    public double getAccuracyPercentage() {
        if (totalAnswers == 0) return 0.0;
        return (double) correctAnswers / totalAnswers * 100;
    }

    public Duration getDuration() {
        Instant end = endTime != null ? endTime : Instant.now();
        return Duration.between(startTime, end);
    }

    public boolean isActive() {
        return state == SessionState.IN_PROGRESS;
    }
}
