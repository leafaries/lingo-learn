package com.lingolearn.vocabulary.set.adapter;

import com.lingolearn.vocabulary.set.adapter.viewmodel.CategoryViewModel;
import com.lingolearn.vocabulary.set.adapter.viewmodel.VocabularySetListViewModel;
import com.lingolearn.vocabulary.set.adapter.viewmodel.VocabularySetViewModel;
import com.lingolearn.vocabulary.set.usecase.VocabularySetInputPort;

public class VocabularySetController {
    private final VocabularySetInputPort setManagement;
    private final VocabularySetPresenter presenter;

    public VocabularySetController(VocabularySetInputPort setManagement, VocabularySetPresenter presenter) {
        this.setManagement = setManagement;
        this.presenter = presenter;
    }

    public VocabularySetViewModel createSet(String name, String description) {
        CreateSetResponse response = setManagement.createSet(
                new CreateSetInputData(name, description)
        );
        return presenter.presentSet(response);
    }

    public VocabularySetViewModel addWords(String setId, List<WordInputData> words) {
        AddWordsResponse response = setManagement.addWords(
                new AddWordsInputData(setId, words)
        );
        return presenter.presentUpdatedSet(response);
    }

    public CategoryViewModel assignCategory(String setId, String categoryName) {
        AssignCategoryResponse response = setManagement.assignCategory(
                new AssignCategoryInputData(setId, categoryName)
        );
        return presenter.presentCategory(response);
    }

    public VocabularySetListViewModel getSets() {
        GetSetsResponse response = setManagement.getSets();
        return presenter.presentSetList(response);
    }

    public void deleteSet(String setId) {
        setManagement.deleteSet(new DeleteSetInputData(setId));
    }
}
