package com.lingolearn.progress.analysis.domain;

import com.lingolearn.vocabulary.category.domain.CategoryHierarchyNode;
import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.util.List;
import java.util.Map;

public record LearningAnalysis(
        Map<Difficulty, Integer> wordsByDifficulty,
        Map<CategoryHierarchyNode, Double> categoryProgress,
        List<ProblemArea> problemAreas
) {
}
