package com.lingolearn.vocabulary.set.usecase;

import com.lingolearn.vocabulary.set.adapter.viewmodel.CategoryViewModel;
import com.lingolearn.vocabulary.set.adapter.viewmodel.VocabularySetListViewModel;
import com.lingolearn.vocabulary.set.adapter.viewmodel.VocabularySetViewModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.AddWordsResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.AssignCategoryResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.CreateSetResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.GetSetsResponseModel;

public interface VocabularySetOutputPort {
    VocabularySetViewModel presentSet(CreateSetResponseModel response);
    VocabularySetViewModel presentUpdatedSet(AddWordsResponseModel response);
    CategoryViewModel presentCategory(AssignCategoryResponseModel response);
    VocabularySetListViewModel presentSetList(GetSetsResponseModel response);
}
