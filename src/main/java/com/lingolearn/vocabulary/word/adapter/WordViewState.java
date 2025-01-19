package com.lingolearn.vocabulary.word.adapter;

import java.util.ArrayList;
import java.util.List;

public record WordViewState(
        WordDisplayModel currentWord,
        List<WordDisplayModel> wordList,
        boolean isLoading,
        String errorMessage
) {
    public static WordViewState initial() {
        return new WordViewState(null, new ArrayList<>(), false, null);
    }

    public WordViewState withCurrentWord(WordDisplayModel word) {
        return new WordViewState(word, this.wordList, this.isLoading, this.errorMessage);
    }

    public WordViewState withWordList(List<WordDisplayModel> words) {
        return new WordViewState(this.currentWord, new ArrayList<>(words), this.isLoading, this.errorMessage);
    }

    public WordViewState withLoading(boolean loading) {
        return new WordViewState(this.currentWord, this.wordList, loading, this.errorMessage);
    }

    public WordViewState withError(String error) {
        return new WordViewState(this.currentWord, this.wordList, this.isLoading, error);
    }
}
