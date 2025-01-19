package com.lingolearn.progress.analysis.domain;

import com.lingolearn.vocabulary.category.domain.CategoryHierarchyNode;
import com.lingolearn.vocabulary.word.domain.Word;

import java.util.List;

public record ProblemArea(
        CategoryHierarchyNode categoryHierarchyNode,
        List<Word> troubleWords,
        double averageAccuracy
) {
}
