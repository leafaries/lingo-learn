package com.lingolearn.vocabulary.set.usecase;

import com.lingolearn.vocabulary.set.usecase.requestmodel.*;

import java.util.List;

public interface VocabularySetInputPort {
    void createSet(CreateSetRequestModel request);
    void updateSet(UpdateSetRequestModel request);
    void deleteSet(Long setId);
    void mergeSets(MergeSetRequestModel request);
    void cloneSet(CloneSetRequestModel request);
    void getSetDetails(Long setId);
    void listSets(FilterSetsRequestModel request);
    void addWordsToSet(Long setId, List<Long> wordIds);
    void removeWordsFromSet(Long setId, List<Long> wordIds);
    void moveToCategory(Long setId, Long categoryId);
    void setActive(Long setId, boolean active);
}
