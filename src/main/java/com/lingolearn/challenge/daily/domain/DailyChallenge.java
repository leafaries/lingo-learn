package com.lingolearn.challenge.daily.domain;

import com.lingolearn.vocabulary.word.domain.Word;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record DailyChallenge(
        Long id,
        LocalDate date,
        List<Word> words,
        ChallengeDifficulty difficulty,
        boolean isCompleted,
        LocalDateTime completedAt
) {
}
