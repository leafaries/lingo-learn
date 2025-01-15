package com.lingolearn.dtos.statistics;

import com.lingolearn.dtos.core.StatisticsDTO;

import java.time.Duration;
import java.time.LocalDate;

/*
 - Generated on demand
 - Represent agreggated data
 - Not stored or referenced directly
 */
public record StudyStatisticsDTO(
        int wordsLearned,
        int correctAnswers,
        int totalAnswers,
        double accuracy,
        Duration studyTime,
        int challengesCompleted,
        LocalDate date
) implements StatisticsDTO {
    @Override
    public int getTotalAnswers() {
        return totalAnswers;
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
        return studyTime;
    }
}
