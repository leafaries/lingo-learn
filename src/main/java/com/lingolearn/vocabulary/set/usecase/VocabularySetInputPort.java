package com.lingolearn.vocabulary.set.usecase;

import com.lingolearn.vocabulary.set.usecase.requestmodel.AddWordsRequestModel;
import com.lingolearn.vocabulary.set.usecase.requestmodel.AssignCategoryRequestModel;
import com.lingolearn.vocabulary.set.usecase.requestmodel.CreateSetRequestModel;
import com.lingolearn.vocabulary.set.usecase.requestmodel.DeleteSetRequestModel;

public interface VocabularySetInputPort {
    void createSet(CreateSetRequestModel request);
    void addWords(AddWordsRequestModel request);
    void assignCategory(AssignCategoryRequestModel request);
    void getSets();
    void deleteSet(DeleteSetRequestModel request);
}
