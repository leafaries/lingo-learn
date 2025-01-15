package com.lingolearn.dtos.statistics;

import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.core.StatisticsDTO;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/*
 - Generated on demand
 - Represent agreggated data
 - Not stored or referenced directly
 */
public record ReportDTO(
        Instant generatedAt,
        ReportConfigDTO config,
        Map<LocalDate, StudyStatisticsDTO> dailyStats,
        List<WordDTO> mostProblematicWords,
        List<VocabularySetDTO> mostStudiedSets,
        int totalWordsLearned,
        double overallAccuracy,
        Duration totalStudyTime
) implements StatisticsDTO {
    @Override
    public int getTotalAnswers() {
        return dailyStats.values().stream()
                .mapToInt(StudyStatisticsDTO::getTotalAnswers)
                .sum();
    }

    @Override
    public int getCorrectAnswers() {
        return dailyStats.values().stream()
                .mapToInt(StudyStatisticsDTO::getCorrectAnswers)
                .sum();
    }

    @Override
    public double getAccuracy() {
        return overallAccuracy;
    }

    @Override
    public Duration getTotalTime() {
        return totalStudyTime;
    }
}
