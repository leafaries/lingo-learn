package com.lingolearn.vocabulary.word.infra.ui;

import com.lingolearn.vocabulary.word.adapter.Observer;
import com.lingolearn.vocabulary.word.adapter.WordController;
import com.lingolearn.vocabulary.word.adapter.WordViewModel;
import com.lingolearn.vocabulary.word.adapter.WordViewState;
import com.lingolearn.vocabulary.word.adapter.dtos.CreateWordDTO;
import com.lingolearn.vocabulary.word.adapter.dtos.RecordAttemptDTO;

public class WordView implements Observer<WordViewState> {
    private final WordController controller;
    private WordViewState currentState;

    public WordView(WordController controller, WordViewModel viewModel) {
        this.controller = controller;
        this.currentState = WordViewState.initial();
        viewModel.addObserver(this);
    }

    @Override
    public void update(WordViewState newState) {
        // Store the new state
        this.currentState = newState;

        // Handle loading state
        if (newState.isLoading()) {
            displayLoadingIndicator();
            return;
        }

        // Handle error state
        if (newState.errorMessage() != null) {
            displayError(newState.errorMessage());
            return;
        }

        // Update the view with new data
        refreshView();
    }

    private void displayLoadingIndicator() {
        System.out.println("Loading...");
    }

    private void displayError(String error) {
        System.out.println("Error: " + error);
    }

    private void refreshView() {
        // Update current word display if exists
        if (currentState.currentWord() != null) {
            System.out.println("Current word: " +
                    currentState.currentWord().original() + " - " +
                    currentState.currentWord().translation());
        }

        // Update word list display
        System.out.println("Word list (" + currentState.wordList().size() + " words):");
        currentState.wordList().forEach(word ->
                System.out.println("- " + word.original() + " - " + word.translation())
        );
    }

    // User interaction methods
    public void createNewWord(CreateWordDTO dto) {
        controller.createWord(dto);
    }

    public void markWordAsLearned(Long wordId) {
        controller.recordAttempt(new RecordAttemptDTO(wordId, true));
    }

    public void deleteCurrentWord() {
        if (currentState.currentWord() != null) {
            controller.deleteWord(currentState.currentWord().id());
        }
    }
}
