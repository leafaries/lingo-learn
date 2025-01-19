package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.domain.Word;
import com.lingolearn.vocabulary.word.domain.WordNotFoundException;
import com.lingolearn.vocabulary.word.usecase.requestmodel.CreateWordRequestModel;
import com.lingolearn.vocabulary.word.usecase.requestmodel.UpdateWordRequestModel;
import com.lingolearn.vocabulary.word.usecase.responsemodel.CreateWordResponseModel;
import com.lingolearn.vocabulary.word.usecase.responsemodel.GetWordResponseModel;
import com.lingolearn.vocabulary.word.usecase.responsemodel.UpdateWordResponseModel;

public class WordInteractor implements WordInteractorInputPort {
    public final WordInteractorOutputPort outputPort;
    public final WordRepository repository;

    public WordInteractor(WordInteractorOutputPort outputPort, WordRepository wordRepository) {
        this.outputPort = outputPort;
        this.repository = wordRepository;
    }

    @Override
    public void createWord(CreateWordRequestModel request) {
        try {
            repository.findByOriginalAndTranslation(request.original(), request.translation())
                    .ifPresent(word -> {
                        throw new IllegalArgumentException("Word with this original and translation already exists");
                    });

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

            var savedWord = repository.save(word);

            var response = new CreateWordResponseModel(
                    savedWord.id(),
                    savedWord.original(),
                    savedWord.translation(),
                    savedWord.partOfSpeech(),
                    savedWord.exampleSentences(),
                    savedWord.difficulty()
            );

            outputPort.presentCreatedWord(response);
        } catch (IllegalArgumentException e) {
            outputPort.presentError(e.getMessage());
        }
    }

    @Override
    public void updateWord(UpdateWordRequestModel request) {
        try {
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

            var response = new UpdateWordResponseModel(
                    savedWord.id(),
                    savedWord.original(),
                    savedWord.translation(),
                    savedWord.partOfSpeech(),
                    savedWord.exampleSentences(),
                    savedWord.difficulty()
            );

            outputPort.presentUpdatedWord(response);
        } catch (WordNotFoundException e) {
            outputPort.presentError(e.getMessage());
        }
    }

    @Override
    public void deleteWord(Long id) {
        try {
            repository.deleteByIdOrThrow(id);
            outputPort.presentWordDeleted(id);
        } catch (WordNotFoundException e) {
            outputPort.presentError(e.getMessage());
        }
    }

    @Override
    public void getWord(Long id) {
        var word = repository.findById(id)
                .orElseThrow(() -> new WordNotFoundException(id));

        var response = new GetWordResponseModel(
                word.id(),
                word.original(),
                word.translation(),
                word.partOfSpeech(),
                word.exampleSentences(),
                word.difficulty(),
                word.lastReviewed(),
                word.timesReviewed(),
                word.correctAnswers()
        );

        outputPort.presentWord(response);
    }

    @Override
    public void getAllWords() {
        var words = repository.findAll();
        var response = words.stream()
                .map(word -> new GetWordResponseModel(
                        word.id(),
                        word.original(),
                        word.translation(),
                        word.partOfSpeech(),
                        word.exampleSentences(),
                        word.difficulty(),
                        word.lastReviewed(),
                        word.timesReviewed(),
                        word.correctAnswers()
                ))
                .toList();

        outputPort.presentWords(response);
    }

    @Override
    public void getWordsByDifficulty(Difficulty difficulty) {
        var words = repository.findByDifficulty(difficulty);
        var response = words.stream()
                .map(word -> new GetWordResponseModel(
                        word.id(),
                        word.original(),
                        word.translation(),
                        word.partOfSpeech(),
                        word.exampleSentences(),
                        word.difficulty(),
                        word.lastReviewed(),
                        word.timesReviewed(),
                        word.correctAnswers()
                ))
                .toList();

        outputPort.presentWords(response);
    }

    @Override
    public void recordAttempt(Long wordId, boolean wasCorrect) {
        var word = repository.findById(wordId)
                .orElseThrow(() -> new WordNotFoundException(wordId));

        var updatedWord = word.recordAttempt(wasCorrect);
        repository.save(updatedWord);
        outputPort.presentAttemptRecorded(wordId);
    }
}
