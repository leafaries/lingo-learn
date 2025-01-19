package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.vocabulary.word.domain.Word;
import com.lingolearn.vocabulary.word.usecase.requestmodel.CreateWordRequestModel;

public class CreateWordUseCase {
    private final WordRepositoryGateway repository;

    public CreateWordUseCase(WordRepositoryGateway repository) {
        this.repository = repository;
    }

    public void execute(CreateWordRequestModel request) {
        var word = new Word(
                null,
                request.original(),
                request.translation(),
                request.partOfSpeech(),
                request.exampleSentences(),
                request.difficulty(),
                null,
                0,
                0
        );

        repository.save(word);
    }
}
