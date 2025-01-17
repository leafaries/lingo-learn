package horizontallayers.interfaceadapters.dtos.study;

import horizontallayers.interfaceadapters.dtos.WordDTO;

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
