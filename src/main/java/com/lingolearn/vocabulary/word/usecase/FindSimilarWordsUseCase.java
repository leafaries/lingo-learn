package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.vocabulary.word.domain.Word;
import com.lingolearn.vocabulary.word.domain.WordNotFoundException;
import com.lingolearn.vocabulary.word.usecase.requestmodel.UpdateWordRequestModel;

public class FindSimilarWordsUseCase {
    private final WordRepositoryGateway repository;

    public FindSimilarWordsUseCase(WordRepositoryGateway repository) {
        this.repository = repository;
    }

    public void execute(UpdateWordRequestModel request) {
        var existingWord = repository.findById(request.id())
                .orElseThrow(() -> new WordNotFoundException(request.id()));

        var updatedWord = new Word(
                existingWord.id(),
                request.original(),
                request.translation(),
                request.partOfSpeech(),
                request.exampleSentences(),
                request.difficulty(),
                existingWord.lastReviewed(),
                existingWord.timesReviewed(),
                existingWord.correctAnswers()
        );

        var savedWord = repository.save(updatedWord);
    }
}
