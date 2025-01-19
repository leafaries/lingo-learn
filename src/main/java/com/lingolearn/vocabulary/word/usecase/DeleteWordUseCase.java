package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.NotImplementedException;

public class DeleteWordUseCase {
    private final WordRepository repository;

    public DeleteWordUseCase(WordRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        // TODO: Impl
        throw new NotImplementedException();
        // TODO: Isn't there a better way?
//        repository.findById(id)
//                .orElseThrow(() -> new WordNotFoundException(id));
//        repository.deleteByIdOrThrow(id);
    }
}
