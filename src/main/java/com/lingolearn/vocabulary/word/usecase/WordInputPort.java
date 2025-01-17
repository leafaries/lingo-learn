package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.vocabulary.word.domain.Word;

import java.util.List;
import java.util.UUID;

public interface WordInputPort {
    WordResponseModel createWord(CreateWordRequestModel request);
    WordResponseModel updateWord(UpdateWordRequestModel request);
    void recordAttempt(UUID wordId, boolean wasCorrect);
    List<Word> findDifficultWords(UUID userId);
    List<Word> findDueForReview(UUID userId);
}
