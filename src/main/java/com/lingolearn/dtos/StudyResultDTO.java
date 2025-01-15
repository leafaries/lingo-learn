package com.lingolearn.dtos;

import com.lingolearn.dtos.core.StatisticsDTO;
import com.lingolearn.dtos.core.TimeTrackedDTO;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public record StudyResultDTO(
        VocabularySetDTO vocabularySet,
        StudyMode mode,
        SessionType type,
        int totalWords,
        int correctAnswers,
        double accuracy,
        Duration totalTime,
        Duration averageResponseTime,
        List<WordDTO> problematicWords,
        Instant startTime,
        Instant endTime
) implements StatisticsDTO, TimeTrackedDTO {

    @Override
    public int getTotalAnswers() {
        return totalWords;
    }

    @Override
    public int getCorrectAnswers() {
        return correctAnswers;
    }

    @Override
    public double getAccuracy() {
        return accuracy;
    }

    @Override
    public Duration getTotalTime() {
        return totalTime;
    }

    @Override
    public Instant getStartTime() {
        return startTime;
    }

    @Override
    public Instant getEndTime() {
        return endTime;
    }
}
