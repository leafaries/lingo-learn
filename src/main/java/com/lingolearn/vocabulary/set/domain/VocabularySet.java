package com.lingolearn.vocabulary.set.domain;

import com.lingolearn.vocabulary.category.domain.Category;
import com.lingolearn.vocabulary.word.domain.Word;

import java.time.LocalDateTime;
import java.util.List;

public record VocabularySet(
        Long id,
        String name,
        String description,
        List<Word> words,
        Category category,
        StudyProgress progress,
        LocalDateTime lastStudied,
        List<StudySession> sessions
) {
}
