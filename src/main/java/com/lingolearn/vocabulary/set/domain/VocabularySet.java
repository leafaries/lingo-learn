package com.lingolearn.vocabulary.set.domain;

import com.lingolearn.progress.metrics.domain.StudyProgress;
import com.lingolearn.progress.metrics.domain.StudySession;
import com.lingolearn.vocabulary.category.domain.CategoryHierarchyNode;
import com.lingolearn.vocabulary.word.domain.Word;

import java.time.LocalDateTime;
import java.util.List;

public record VocabularySet(
        Long id,
        String name,
        String description,
        List<Word> words,
        CategoryHierarchyNode categoryHierarchyNode,
        StudyProgress progress,
        LocalDateTime lastStudied,
        List<StudySession> sessions,
        int targetDailyWords,
        boolean isActive
) {
    public List<Word> getWordsNeedingReview() {
        return words.stream()
                .filter(Word::needsReview)
                .toList();
    }
}
