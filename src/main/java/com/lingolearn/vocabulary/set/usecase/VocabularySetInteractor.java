package com.lingolearn.vocabulary.set.usecase;

import com.lingolearn.NotImplementedException;
import com.lingolearn.vocabulary.set.usecase.requestmodel.*;

import java.util.List;

public class VocabularySetInteractor implements VocabularySetInputPort {
    @Override
    public void createSet(CreateSetRequestModel request) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void updateSet(UpdateSetRequestModel request) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void deleteSet(Long setId) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void mergeSets(MergeSetRequestModel request) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void cloneSet(CloneSetRequestModel request) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void getSetDetails(Long setId) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void listSets(FilterSetsRequestModel request) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void addWordsToSet(Long setId, List<Long> wordIds) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void removeWordsFromSet(Long setId, List<Long> wordIds) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void moveToCategory(Long setId, Long categoryId) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void setActive(Long setId, boolean active) {
        // TODO: Impl
        throw new NotImplementedException();
    }
}
