package com.lingolearn.vocabulary.set.usecase;

import com.lingolearn.vocabulary.set.usecase.requestmodel.AddWordsRequestModel;
import com.lingolearn.vocabulary.set.usecase.requestmodel.AssignCategoryRequestModel;
import com.lingolearn.vocabulary.set.usecase.requestmodel.CreateSetRequestModel;
import com.lingolearn.vocabulary.set.usecase.requestmodel.DeleteSetRequestModel;

public class VocabularySetInteractor implements VocabularySetInputPort {
    private final VocabularySetRepository repository;
    private final VocabularySetOutputPort outputPort;

    public VocabularySetInteractor(
            VocabularySetOutputPort outputPort,
            VocabularySetRepository repository
    ) {
        this.outputPort = outputPort;
        this.repository = repository;
    }

    @Override
    public void createSet(CreateSetRequestModel request) {
        // TODO: Impl
//        var set = new VocabularySet(
//                UUID.randomUUID(),
//                request.name(),
//                request.description(),
//                new ArrayList<>()
//        );
//
//        var savedSet = repository.save(set);
//        return new CreateSetResponseModel(
//                savedSet.id(),
//                savedSet.name(),
//                savedSet.description(),
//                savedSet.words()
//        );
    }

    @Override
    public void addWords(AddWordsRequestModel request) {
        // TODO: Impl
//        var set = repository.findById(request.setId())
//                .orElseThrow(() -> new VocabularySetNotFoundException(request.setId()));
//
//        var updatedWords = new ArrayList<>(set.words());
//        updatedWords.addAll(request.words());
//
//        var updatedSet = new VocabularySet(
//                set.id(),
//                set.name(),
//                set.description(),
//                updatedWords
//        );
//
//        var savedSet = repository.save(updatedSet);
//        return new AddWordsResponseModel(
//                savedSet.id(),
//                savedSet.name(),
//                savedSet.description(),
//                savedSet.words()
//        );
    }

    @Override
    public void assignCategory(AssignCategoryRequestModel request) {
        // TODO: Impl
    }

    @Override
    public void getSets() {
        // TODO: Impl
    }

    @Override
    public void deleteSet(DeleteSetRequestModel request) {
        // TODO: Impl
    }
}
