package com.lingolearn.progress.metrics.domain;

import com.lingolearn.vocabulary.category.domain.CategoryHierarchyNode;

import java.util.Map;

public record OverallProgress(
        int totalSets,
        int totalWords,
        int masteredWords,
        double averageAccuracy,
        Map<CategoryHierarchyNode, Integer> wordsByCategory) {
}
