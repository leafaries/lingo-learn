package com.lingolearn.persistence.settings.domain;

import com.lingolearn.vocabulary.word.domain.DifficultyLevel;

public record NotificationSettings(
        int dailyWordGoal,
        List<StudyMode> preferredModes,
        boolean useSpacedRepetition,
        DifficultyLevel preferredDifficulty
) {
}
