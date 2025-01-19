package com.lingolearn.vocabulary.category.usecase;

import com.lingolearn.progress.metrics.domain.StudyProgress;

record CategoryStatisticsResponseModel(
        Long categoryId,
        int totalSets,
        int totalWords,
        double averageAccuracy,
        StudyProgress progress
) {
}
