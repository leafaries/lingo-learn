package com.lingolearn.vocabulary.set.adapter;

import com.lingolearn.vocabulary.set.adapter.viewmodel.CategoryViewModel;
import com.lingolearn.vocabulary.set.adapter.viewmodel.VocabularySetListViewModel;
import com.lingolearn.vocabulary.set.adapter.viewmodel.VocabularySetViewModel;
import com.lingolearn.vocabulary.set.usecase.VocabularySetOutputPort;
import com.lingolearn.vocabulary.set.usecase.responsemodel.AddWordsResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.AssignCategoryResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.CreateSetResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.GetSetsResponseModel;

public class VocabularySetPresenter implements VocabularySetOutputPort {
    @Override
    public VocabularySetViewModel presentSet(CreateSetResponseModel response) {
        // TODO: Impl
        return null;
    }

    @Override
    public VocabularySetViewModel presentUpdatedSet(AddWordsResponseModel response) {
        // TODO: Impl
        return null;
    }

    @Override
    public CategoryViewModel presentCategory(AssignCategoryResponseModel response) {
        // TODO: Impl
        return null;
    }

    @Override
    public VocabularySetListViewModel presentSetList(GetSetsResponseModel response) {
        // TODO: Impl
        return null;
    }
}
