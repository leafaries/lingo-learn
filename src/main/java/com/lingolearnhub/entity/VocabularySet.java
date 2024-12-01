package com.lingolearnhub.entity;

import java.util.ArrayList;
import java.util.List;

public class VocabularySet {
    private final String name;
    private final List<Word> words;

    public VocabularySet(String name) {
        this.name = name;
        this.words = new ArrayList<>();
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public List<Word> getWords() {
        return words;
    }

    public String getName() {
        return name;
    }
}
