package com.lingolearn.progress.report.domain;

import com.lingolearn.vocabulary.category.domain.Category;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProgressReport(
        UUID id,
        UUID userId,
        LocalDateTime generatedAt,
        ReportTimeframe timeframe,
        List<StudySession> sessions,
        Map<Category, CategoryProgress> progressByCategory,
        OverallProgress overallProgress
) {
}
