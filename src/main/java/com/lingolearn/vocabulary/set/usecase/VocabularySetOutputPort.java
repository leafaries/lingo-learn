package com.lingolearn.vocabulary.set.usecase;

import com.lingolearn.vocabulary.set.usecase.responsemodel.VocabularySetDetailResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.VocabularySetListResponseModel;

public interface VocabularySetOutputPort {
    void presentSetDetails(VocabularySetDetailResponseModel response);
    void presentSetList(VocabularySetListResponseModel response);
    void presentMergeResult(MergeSetResponseModel response);
    void presentError(String errorMessage);
    void presentSuccess(String message);
}
