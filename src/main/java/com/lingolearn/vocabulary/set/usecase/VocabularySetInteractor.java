package com.lingolearn.vocabulary.set.usecase;

import com.lingolearn.vocabulary.set.domain.VocabularySet;
import com.lingolearn.vocabulary.set.domain.exception.VocabularySetNotFoundException;
import com.lingolearn.vocabulary.set.usecase.requestmodel.AddWordsRequestModel;
import com.lingolearn.vocabulary.set.usecase.requestmodel.AssignCategoryRequestModel;
import com.lingolearn.vocabulary.set.usecase.requestmodel.CreateSetRequestModel;
import com.lingolearn.vocabulary.set.usecase.requestmodel.DeleteSetRequestModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.AddWordsResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.AssignCategoryResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.CreateSetResponseModel;
import com.lingolearn.vocabulary.set.usecase.responsemodel.GetSetsResponseModel;

import java.util.ArrayList;
import java.util.UUID;

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
    public CreateSetResponseModel createSet(CreateSetRequestModel request) {
        var set = new VocabularySet(
                UUID.randomUUID(),
                request.name(),
                request.description(),
                new ArrayList<>()
        );

        var savedSet = repository.save(set);
        return new CreateSetResponseModel(
                savedSet.id(),
                savedSet.name(),
                savedSet.description(),
                savedSet.words()
        );
    }

    @Override
    public AddWordsResponseModel addWords(AddWordsRequestModel request) {
        var set = repository.findById(request.setId())
                .orElseThrow(() -> new VocabularySetNotFoundException(request.setId()));

        var updatedWords = new ArrayList<>(set.words());
        updatedWords.addAll(request.words());

        var updatedSet = new VocabularySet(
                set.id(),
                set.name(),
                set.description(),
                updatedWords
        );

        var savedSet = repository.save(updatedSet);
        return new AddWordsResponseModel(
                savedSet.id(),
                savedSet.name(),
                savedSet.description(),
                savedSet.words()
        );
    }

    @Override
    public AssignCategoryResponseModel assignCategory(AssignCategoryRequestModel request) {
        return null;
    }

    @Override
    public GetSetsResponseModel getSets() {
        return null;
    }

    @Override
    public void deleteSet(DeleteSetRequestModel request) {

    }
}
