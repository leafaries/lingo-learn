package com.lingolearn.progress.analysis.domain;

import com.lingolearn.vocabulary.category.domain.Category;
import com.lingolearn.vocabulary.word.domain.Word;

import java.util.List;

public record ProblemArea(
        Category category,
        List<Word> troubleWords,
        double averageAccuracy
) {
}
