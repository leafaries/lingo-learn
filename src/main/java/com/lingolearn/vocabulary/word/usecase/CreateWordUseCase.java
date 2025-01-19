package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.NotImplementedException;
import com.lingolearn.vocabulary.word.usecase.requestmodel.CreateWordRequestModel;

public class CreateWordUseCase {
    private final WordRepository repository;

    public CreateWordUseCase(WordRepository repository) {
        this.repository = repository;
    }

    public void execute(CreateWordRequestModel request) {
        // TODO: Impl
        throw new NotImplementedException();
//        var word = new Word(
//                null,
//                request.original(),
//                request.translation(),
//                request.partOfSpeech(),
//                request.exampleSentences(),
//                request.difficulty(),
//                null,
//                0,
//                0
//        );
//
//        repository.save(word);
    }
}
