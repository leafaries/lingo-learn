package com.lingolearn.vocabulary.word.adapter.dtos;

public record RecordAttemptDTO(
        Long wordId,
        boolean wasCorrect
) {
}
