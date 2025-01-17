package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.vocabulary.word.domain.Word;
import com.lingolearn.vocabulary.word.domain.WordNotFoundException;
import com.lingolearn.vocabulary.word.usecase.requestmodel.CreateWordRequestModel;
import com.lingolearn.vocabulary.word.usecase.requestmodel.RecordAttemptRequestModel;
import com.lingolearn.vocabulary.word.usecase.requestmodel.UpdateWordRequestModel;
import com.lingolearn.vocabulary.word.usecase.responsemodel.WordResponseModel;

import java.util.List;
import java.util.Optional;

public class WordInteractorInteractor implements WordInteractorInputPort {
    private final WordRepository repository;
    private final WordInteractorOutputPort outputPort;

    public WordInteractorInteractor(WordRepository repository, WordInteractorOutputPort outputPort) {
        this.repository = repository;
        this.outputPort = outputPort;
    }

    @Override
    public WordResponseModel createWord(CreateWordRequestModel request) {
        Word word = new Word(
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

        Word savedWord = repository.save(word);
        return mapToResponseModel(savedWord);
    }

    @Override
    public WordResponseModel updateWord(UpdateWordRequestModel request) {
        Word existingWord = repository.findById(request.id())
                .orElseThrow(() -> new WordNotFoundException(request.id()));

        Word updatedWord = new Word(
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

        Word savedWord = repository.save(updatedWord);
        return mapToResponseModel(savedWord);
    }

    @Override
    public void recordAttempt(RecordAttemptRequestModel request) {
        Word word = repository.findById(request.wordId())
                .orElseThrow(() -> new WordNotFoundException(request.wordId()));

        Word updatedWord = word.recordAttempt(request.wasCorrect());
        repository.save(updatedWord);
    }

    @Override
    public List<Word> findDifficultWords() {
        // TODO: Impl
        return repository.findByDifficulty();
    }

    @Override
    public List<Word> findDueForReview() {
        return repository.findDueForReview();
    }

    @Override
    public Optional<Word> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Word> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteWord(Long id) {

    }

    private WordResponseModel mapToResponseModel(Word word) {
        return new WordResponseModel(
                word.id(),
                word.original(),
                word.translation(),
                word.partOfSpeech(),
                word.exampleSentences(),
                word.difficulty(),
                word.lastReviewed(),
                word.timesReviewed(),
                word.correctAnswers(),
                word.getSuccessRate()
        );
    }
}
