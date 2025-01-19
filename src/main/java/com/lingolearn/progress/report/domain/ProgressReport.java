package com.lingolearn.progress.report.domain;

import com.lingolearn.progress.metrics.domain.CategoryProgress;
import com.lingolearn.progress.metrics.domain.OverallProgress;
import com.lingolearn.progress.metrics.domain.StudySession;
import com.lingolearn.vocabulary.category.domain.CategoryHierarchyNode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record ProgressReport(
        UUID id,
        UUID userId,
        LocalDateTime generatedAt,
        ReportTimeframe timeframe,
        List<StudySession> sessions,
        Map<CategoryHierarchyNode, CategoryProgress> progressByCategory,
        OverallProgress overallProgress
) {
}
