package com.lingolearn.dtos.study;

import com.lingolearn.dtos.WordDTO;

import java.time.Duration;
import java.time.Instant;

public record AnswerDTO(
        WordDTO word,
        String userAnswer,
        boolean correct,
        Duration responseTime,
        Instant timestamp
) {

}
