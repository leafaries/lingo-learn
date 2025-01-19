package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.NotImplementedException;
import com.lingolearn.vocabulary.word.usecase.requestmodel.UpdateWordRequestModel;

public class UpdateWordDifficultyUseCase {
    private final WordRepository repository;

    public UpdateWordDifficultyUseCase(WordRepository repository) {
        this.repository = repository;
    }

    public void execute(UpdateWordRequestModel request) {
        // TODO: Impl
        throw new NotImplementedException();
//        var existingWord = repository.findById(request.id())
//                .orElseThrow(() -> new WordNotFoundException(request.id()));
//
//        var updatedWord = new Word(
//                existingWord.id(),
//                request.original(),
//                request.translation(),
//                request.partOfSpeech(),
//                request.exampleSentences(),
//                request.difficulty(),
//                existingWord.lastReviewed(),
//                existingWord.timesReviewed(),
//                existingWord.correctAnswers()
//        );
//
//        var savedWord = repository.save(updatedWord);
    }
}
