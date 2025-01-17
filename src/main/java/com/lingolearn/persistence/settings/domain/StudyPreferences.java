package com.lingolearn.persistence.settings.domain;

import com.lingolearn.vocabulary.word.domain.DifficultyLevel;

public record StudyPreferences(
        int dailyWordGoal,
        List<StudyMode> preferredModes,
        boolean useSpacedRepetition,
        DifficultyLevel preferredDifficulty
) {
}
