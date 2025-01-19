package com.lingolearn.vocabulary.set.adapter;

import com.lingolearn.NotImplementedException;
import com.lingolearn.vocabulary.set.usecase.MergeSetResponseModel;
import com.lingolearn.vocabulary.set.usecase.VocabularySetOutputPort;
import com.lingolearn.vocabulary.set.usecase.responsemodel.VocabularySetDetailResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.VocabularySetListResponseModel;

public class VocabularySetPresenter implements VocabularySetOutputPort {
    @Override
    public void presentSetDetails(VocabularySetDetailResponseModel response) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void presentSetList(VocabularySetListResponseModel response) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void presentMergeResult(MergeSetResponseModel response) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void presentError(String errorMessage) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void presentSuccess(String message) {
        // TODO: Impl
        throw new NotImplementedException();
    }
}
