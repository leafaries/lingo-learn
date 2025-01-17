package com.lingolearn.persistence.settings.domain;

import com.lingolearn.vocabulary.word.domain.Difficulty;

public record NotificationSettings(
        int dailyWordGoal,
        List<StudyMode> preferredModes,
        boolean useSpacedRepetition,
        Difficulty preferredDifficulty
) {
}
