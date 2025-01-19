package com.lingolearn.vocabulary.word.adapter;

import java.util.ArrayList;
import java.util.List;

public class WordViewModel implements Observable<WordViewState> {
    private final List<Observer<WordViewState>> observers;
    private WordViewState state;

    public WordViewModel() {
        this.state = WordViewState.initial();
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer<WordViewState> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<WordViewState> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (var observer : observers) {
            observer.update(state);
        }
    }

    // State modification methods - each automatically notifies observers
    public void setCurrentWord(WordDisplayModel word) {
        this.state = state.withCurrentWord(word);
        notifyObservers();
    }

    public void setWordList(List<WordDisplayModel> words) {
        this.state = state.withWordList(words);
        notifyObservers();
    }

    public void addWord(WordDisplayModel word) {
        var newList = new ArrayList<>(state.wordList());
        newList.add(word);
        this.state = state.withWordList(newList);
        notifyObservers();
    }

    public void updateWord(WordDisplayModel updatedWord) {
        var newList = new ArrayList<>(state.wordList());
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).id().equals(updatedWord.id())) {
                newList.set(i, updatedWord);
                break;
            }
        }
        this.state = state.withWordList(newList);
        if (state.currentWord() != null && state.currentWord().id().equals(updatedWord.id())) {
            this.state = state.withCurrentWord(updatedWord);
        }
        notifyObservers();
    }

    public void removeWord(Long id) {
        var newList = state.wordList().stream()
                .filter(w -> !w.id().equals(id))
                .toList();
        this.state = state.withWordList(newList);
        if (state.currentWord() != null && state.currentWord().id().equals(id)) {
            this.state = state.withCurrentWord(null);
        }
        notifyObservers();
    }

    public void setLoading(boolean loading) {
        this.state = state.withLoading(loading);
        notifyObservers();
    }

    public void setError(String error) {
        this.state = state.withError(error);
        notifyObservers();
    }

    // Getter for current state
    public WordViewState getState() {
        return state;
    }
}
