package com.lingolearn.challenge.daily.domain;

import com.lingolearn.vocabulary.word.domain.Word;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record DailyChallenge(
        UUID id,
        LocalDate date,
        List<Word> words,
        ChallengeDifficulty difficulty,
        boolean isCompleted,
        LocalDateTime completedAt
) {
}
