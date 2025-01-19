package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.usecase.requestmodel.CreateWordRequestModel;
import com.lingolearn.vocabulary.word.usecase.requestmodel.UpdateWordRequestModel;

public interface WordInteractorInputPort {
    void createWord(CreateWordRequestModel request);
    void updateWord(UpdateWordRequestModel request);
    void deleteWord(Long id);
    void getWord(Long id);
    void getAllWords();
    void getWordsByDifficulty(Difficulty difficulty);
    void recordAttempt(Long wordId, boolean wasCorrect);
}
