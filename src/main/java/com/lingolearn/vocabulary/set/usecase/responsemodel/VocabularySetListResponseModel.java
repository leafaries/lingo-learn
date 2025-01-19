package com.lingolearn.vocabulary.set.usecase.responsemodel;

import com.lingolearn.vocabulary.category.domain.Category;
import com.lingolearn.vocabulary.set.domain.VocabularySetSummary;
import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.util.List;
import java.util.Map;

public record VocabularySetListResponseModel(
        List<VocabularySetSummary> sets,
        int totalSets,
        Map<Category, Integer> setsByCategory,
        Map<Difficulty, Integer> setsByDifficulty
) {
}
