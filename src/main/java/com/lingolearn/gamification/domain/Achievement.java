package com.lingolearn.gamification.domain;

import java.time.LocalDateTime;

public record Achievement(
        Long id,
        String name,
        String description,
        AchievementType type,
        int requiredCount,
        boolean isUnlocked,
        LocalDateTime unlockedAt
) {
}
