package com.lingolearn.vocabulary.word.usecase.requestmodel;

public record RecordAttemptRequestModel(
        Long wordId,
        boolean wasCorrect
) {
}
