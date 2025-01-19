package com.lingolearn.vocabulary.set.adapter;

import com.lingolearn.vocabulary.set.usecase.VocabularySetOutputPort;
import com.lingolearn.vocabulary.set.usecase.responsemodel.AddWordsResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.AssignCategoryResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.CreateSetResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.GetSetsResponseModel;

public class VocabularySetPresenter implements VocabularySetOutputPort {
    @Override
    public void presentSet(CreateSetResponseModel response) {
        // TODO: Impl
    }

    @Override
    public void presentUpdatedSet(AddWordsResponseModel response) {
        // TODO: Impl
    }

    @Override
    public void presentCategory(AssignCategoryResponseModel response) {
        // TODO: Impl
    }

    @Override
    public void presentSetList(GetSetsResponseModel response) {
        // TODO: Impl
    }
}
