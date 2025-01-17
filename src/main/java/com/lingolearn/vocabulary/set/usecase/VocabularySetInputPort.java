package com.lingolearn.vocabulary.set.usecase;

import com.lingolearn.vocabulary.set.usecase.requestmodel.AddWordsRequestModel;
import com.lingolearn.vocabulary.set.usecase.requestmodel.AssignCategoryRequestModel;
import com.lingolearn.vocabulary.set.usecase.requestmodel.CreateSetRequestModel;
import com.lingolearn.vocabulary.set.usecase.requestmodel.DeleteSetRequestModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.AddWordsResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.AssignCategoryResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.CreateSetResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.GetSetsResponseModel;

public interface VocabularySetInputPort {
    CreateSetResponseModel createSet(CreateSetRequestModel request);
    AddWordsResponseModel addWords(AddWordsRequestModel request);
    AssignCategoryResponseModel assignCategory(AssignCategoryRequestModel request);
    GetSetsResponseModel getSets();
    void deleteSet(DeleteSetRequestModel request);
}
