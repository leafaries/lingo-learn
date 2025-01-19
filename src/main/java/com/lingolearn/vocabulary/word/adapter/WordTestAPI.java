package com.lingolearn.vocabulary.word.adapter;

import com.lingolearn.common.adapter.TestStateManager;
import com.lingolearn.vocabulary.word.adapter.dtos.CreateWordDTO;
import com.lingolearn.vocabulary.word.adapter.dtos.RecordAttemptDTO;
import com.lingolearn.vocabulary.word.adapter.dtos.UpdateWordDTO;
import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.domain.Word;
import com.lingolearn.vocabulary.word.usecase.WordInteractor;
import com.lingolearn.vocabulary.word.usecase.responsemodel.GetWordResponseModel;

/**
 * API for testing word-related functionality
 */
public class WordTestAPI {
    private final WordController controller;
    private final WordViewModel viewModel;
    private final TestStateManager stateManager;

    public WordTestAPI(TestStateManager stateManager) {
        this.stateManager = stateManager;
        this.viewModel = new WordViewModel();
        var presenter = new WordPresenter(viewModel);
        var interactor = new WordInteractor(presenter, stateManager.getWordRepository());
        this.controller = new WordController(interactor);
    }

    public WordViewModel getViewModel() {
        return viewModel;
    }

    public void createWord(CreateWordDTO dto) {
        controller.createWord(dto);
    }

    public void deleteWord(Long id) {
        controller.deleteWord(id);
    }

    public void updateWord(UpdateWordDTO dto) {
        controller.updateWord(dto);
    }

    public void recordAttempt(Long wordId, boolean wasCorrect) {
        controller.recordAttempt(new RecordAttemptDTO(wordId, wasCorrect));
    }

    public GetWordResponseModel getWordDetails(Long id) {
        controller.getWord(id);
        var currentWord = viewModel.getState().currentWord();
        return new GetWordResponseModel(
                currentWord.id(),
                currentWord.original(),
                currentWord.translation(),
                currentWord.partOfSpeech(),
                currentWord.exampleSentences(),
                currentWord.difficulty(),
                null, // lastReviewed - from repository
                getWordFromRepository(id).timesReviewed(),
                getWordFromRepository(id).correctAnswers()
        );
    }

    public void getAllWords() {
        controller.getAllWords();
    }

    public void getWordsByDifficulty(Difficulty difficulty) {
        controller.getWordsByDifficulty(difficulty);
    }

    // Test assertion helpers
    public boolean wordExists(Long id) {
        return stateManager.getWordRepository().findById(id).isPresent();
    }

    public int getWordCount() {
        return stateManager.getWordRepository().findAll().size();
    }

    private Word getWordFromRepository(Long id) {
        return stateManager.getWordRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Word not found with id: " + id));
    }

    public void reset() {
        stateManager.reset();
    }
}
