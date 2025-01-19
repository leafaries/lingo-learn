package com.lingolearn.vocabulary.set.domain;

import com.lingolearn.vocabulary.category.domain.Category;

import java.time.LocalDateTime;

public record VocabularySetSummary(
        Long id,
        String name,
        String description,
        int wordCount,
        double progressPercentage,
        LocalDateTime lastStudied,
        Category category,
        boolean isActive
) {
}
