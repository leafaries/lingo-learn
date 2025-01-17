package com.lingolearn.progress.metrics.domain;

import com.lingolearn.vocabulary.word.domain.Word;

import java.time.LocalDateTime;
import java.util.List;

public record StudySession(
        Long id,
        Long setId,
        StudyMode mode,
        LocalDateTime startTime,
        LocalDateTime endTime,
        int correctAnswers,
        int totalQuestions,
        List<Word> mistakesMade) {
}
