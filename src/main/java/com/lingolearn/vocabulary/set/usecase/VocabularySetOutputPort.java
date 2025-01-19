package com.lingolearn.vocabulary.set.usecase;

import com.lingolearn.vocabulary.set.usecase.responsemodel.AddWordsResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.AssignCategoryResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.CreateSetResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.GetSetsResponseModel;

public interface VocabularySetOutputPort {
    void presentSet(CreateSetResponseModel response);
    void presentUpdatedSet(AddWordsResponseModel response);
    void presentCategory(AssignCategoryResponseModel response);
    void presentSetList(GetSetsResponseModel response);
}
