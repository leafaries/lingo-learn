package com.lingolearn.vocabulary.word.adapter;

import com.lingolearn.NotImplementedException;
import com.lingolearn.vocabulary.word.adapter.dtos.CreateWordDTO;
import com.lingolearn.vocabulary.word.adapter.dtos.UpdateWordDTO;
import com.lingolearn.vocabulary.word.usecase.WordInteractorInputPort;
import com.lingolearn.vocabulary.word.usecase.requestmodel.CreateWordRequestModel;
import com.lingolearn.vocabulary.word.usecase.requestmodel.UpdateWordRequestModel;

public class WordController {
    private final WordInteractorInputPort wordInteractor;

    public WordController(WordInteractorInputPort wordInteractor) {
        this.wordInteractor = wordInteractor;
    }

    public void createWord(CreateWordDTO dto) {
        var createWordRequestModel = new CreateWordRequestModel(
                dto.original(),
                dto.translation(),
                dto.partOfSpeech(),
                dto.exampleSentences(),
                dto.difficulty()
        );

        // TODO: Impl
        throw new NotImplementedException();
//        createWordUseCase.execute(createWordRequestModel);
    }

    public void updateWord(UpdateWordDTO dto) {
        var updateWordRequestModel = new UpdateWordRequestModel(
                dto.id(),
                dto.original(),
                dto.translation(),
                dto.partOfSpeech(),
                dto.exampleSentences(),
                dto.difficulty()
        );

        // TODO: Impl
        throw new NotImplementedException();
//        updateWordUseCase.execute(updateWordRequestModel);
    }

    public void deleteWord(Long id) {
        // TODO: Impl
        throw new NotImplementedException();
//        deleteWordUseCase.execute(id);
    }
}
