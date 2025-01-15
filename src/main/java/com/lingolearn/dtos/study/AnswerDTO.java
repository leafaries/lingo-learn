package com.lingolearn.dtos.study;

import com.lingolearn.dtos.WordDTO;

import java.time.Duration;
import java.time.Instant;

/*
 - It's a transient record of a users's response
 - Never referenced directly by other operations
 - Part of session/study results
 */
public record AnswerDTO(
        WordDTO word,
        String userAnswer,
        boolean correct,
        Duration responseTime,
        Instant timestamp
) {

}
