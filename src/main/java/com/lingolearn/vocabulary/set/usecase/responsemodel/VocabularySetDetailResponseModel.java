package com.lingolearn.vocabulary.set.usecase.responsemodel;

import com.lingolearn.progress.metrics.domain.StudyProgress;
import com.lingolearn.progress.metrics.domain.StudySession;
import com.lingolearn.vocabulary.category.domain.Category;
import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.domain.Word;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record VocabularySetDetailResponseModel(
        Long id,
        String name,
        String description,
        Category category,
        List<Word> words,
        StudyProgress progress,
        LocalDateTime lastStudied,
        int targetDailyWords,
        boolean isActive,
        List<StudySession> recentSessions,
        Map<Difficulty, Integer> difficultyDistribution
) {
}
