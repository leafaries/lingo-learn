package com.lingolearn.vocabulary.word.domain;

public class WordNotFoundException extends RuntimeException {
    public WordNotFoundException(Long id) {
        super("Word not found with id: " + id);
    }

    public WordNotFoundException(String word) {
        super("Word not found: " + word);
    }
}
