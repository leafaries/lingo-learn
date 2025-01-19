package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.vocabulary.word.usecase.responsemodel.CreateWordResponseModel;
import com.lingolearn.vocabulary.word.usecase.responsemodel.GetWordResponseModel;
import com.lingolearn.vocabulary.word.usecase.responsemodel.UpdateWordResponseModel;

import java.util.List;

public interface WordInteractorOutputPort {
    void presentCreatedWord(CreateWordResponseModel response);
    void presentUpdatedWord(UpdateWordResponseModel response);
    void presentWord(GetWordResponseModel response);
    void presentWords(List<GetWordResponseModel> response);
    void presentWordDeleted(Long id);
    void presentAttemptRecorded(Long wordId);
}
