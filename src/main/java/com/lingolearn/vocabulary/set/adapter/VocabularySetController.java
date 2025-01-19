package com.lingolearn.vocabulary.set.adapter;

import com.lingolearn.vocabulary.set.usecase.VocabularySetInputPort;

public class VocabularySetController {
    private final VocabularySetInputPort setManagement;
    private final VocabularySetPresenter presenter;

    public VocabularySetController(VocabularySetInputPort setManagement, VocabularySetPresenter presenter) {
        this.setManagement = setManagement;
        this.presenter = presenter;
    }

    public void createSet(String name, String description) {
        // TODO: Impl
//        CreateSetResponse response = setManagement.createSet(
//                new CreateSetInputData(name, description)
//        );
//        return presenter.presentSet(response);
    }

    //        public void addWords(String setId, List<WordInputData> words) {
    public void addWords() {
        // TODO: Impl
//        AddWordsResponse response = setManagement.addWords(
//                new AddWordsInputData(setId, words)
//        );
//        return presenter.presentUpdatedSet(response);
    }

    public void assignCategory(String setId, String categoryName) {
        // TODO: Impl
//        AssignCategoryResponse response = setManagement.assignCategory(
//                new AssignCategoryInputData(setId, categoryName)
//        );
//        return presenter.presentCategory(response);
    }

    public void getSets() {
        // TODO: Impl
//        GetSetsResponse response = setManagement.getSets();
//        return presenter.presentSetList(response);
    }

    public void deleteSet(String setId) {
        // TODO: Impl
//        setManagement.deleteSet(new DeleteSetInputData(setId));
    }
}
