package com.lingolearn.dtos.statistics;

import java.time.Duration;
import java.time.LocalDate;

public record StudyStatisticsDTO(
        int wordsLearned,
        int correctAnswers,
        int totalAnswers,
        double accuracy,
        Duration studyTime,
        int challengesCompleted,
        LocalDate date
) {

}
