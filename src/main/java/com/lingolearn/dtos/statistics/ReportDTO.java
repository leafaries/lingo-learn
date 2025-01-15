package com.lingolearn.dtos.statistics;

import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record ReportDTO(
        Instant generatedAt,
        ReportConfigDTO config,
        Map<LocalDate, StudyStatisticsDTO> dailyStats,
        List<WordDTO> mostProblematicWords,
        List<VocabularySetDTO> mostStudiedSets,
        int totalWordsLearned,
        double overallAccuracy,
        Duration totalStudyTime
) {

}
