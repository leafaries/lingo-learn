package com.lingolearn.progress.analysis.domain;

import com.lingolearn.vocabulary.category.domain.Category;
import com.lingolearn.vocabulary.word.domain.DifficultyLevel;

import java.util.List;
import java.util.Map;

public record LearningAnalysis(
        Map<DifficultyLevel, Integer> wordsByDifficulty,
        Map<Category, Double> categoryProgress,
        List<ProblemArea> problemAreas
) {
}
