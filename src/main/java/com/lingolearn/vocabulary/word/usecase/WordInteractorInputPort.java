package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.vocabulary.word.domain.Word;
import com.lingolearn.vocabulary.word.usecase.requestmodel.CreateWordRequestModel;
import com.lingolearn.vocabulary.word.usecase.requestmodel.RecordAttemptRequestModel;
import com.lingolearn.vocabulary.word.usecase.requestmodel.UpdateWordRequestModel;
import com.lingolearn.vocabulary.word.usecase.responsemodel.WordResponseModel;

import java.util.List;
import java.util.Optional;

public interface WordInteractorInputPort {
    WordResponseModel createWord(CreateWordRequestModel request);
    WordResponseModel updateWord(UpdateWordRequestModel request);
    void recordAttempt(RecordAttemptRequestModel request);
    List<Word> findDifficultWords();
    List<Word> findDueForReview();
    Optional<Word> findById(Long id);
    List<Word> findAll();
    void deleteWord(Long id);
}
