package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.vocabulary.word.domain.WordNotFoundException;

public class DeleteWordUseCase {
    private final WordRepositoryGateway repository;

    public DeleteWordUseCase(WordRepositoryGateway repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        // TODO: Isn't there a better way?
        repository.findById(id)
                .orElseThrow(() -> new WordNotFoundException(id));
        repository.deleteByIdOrThrow(id);
    }
}
