package com.lingolearn.gamification.domain;

import java.time.LocalDate;
import java.util.Map;

public record StudyStreak(
        Long id,
        int currentStreak,
        int longestStreak,
        LocalDate lastStudyDate,
        Map<LocalDate, Integer> dailyWordCounts
) {
    public boolean wouldBreakStreak(LocalDate date) {
        return lastStudyDate.until(date).getDays() > 1;
    }

    public boolean isActive() {
        return !wouldBreakStreak(LocalDate.now());
    }
}
