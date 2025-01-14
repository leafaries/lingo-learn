package com.lingolearn.dtos;

import com.lingolearn.enums.SessionState;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;

import java.time.Instant;
import java.util.List;

public record AnswerDTO(
        VocabularySetDTO vocabularySetDTO,
        StudyMode studyMode,
        SessionType type,
        SessionState state,
        Instant startTime,
        Instant endTime,
        List<AnswerDTO> answers
) {

}
