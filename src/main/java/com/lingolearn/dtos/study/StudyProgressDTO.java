package com.lingolearn.dtos.study;

import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.core.StatisticsDTO;

import java.time.Duration;

public record StudyProgressDTO(
        WordDTO currentWord,
        boolean isCorrect,
        int totalAnswered,
        int correctAnswers,
        double accuracy,
        Duration averageResponseTime
) implements StatisticsDTO {
    @Override
    public int getTotalAnswers() {
        return totalAnswered;
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
        return averageResponseTime.multipliedBy(totalAnswered);
    }
}
