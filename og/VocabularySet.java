package com.og;

import com.lingolearnhub.og.Word;

import java.util.ArrayList;
import java.util.List;

public class VocabularySet {
    private final String name;
    private final List<Word> words;

    public VocabularySet(String name) {
        this.name = name;
        this.words = new ArrayList<>();
    }

    public void addWord(com.lingolearnhub.og.entity.Word word) {
        words.add(word);
    }

    public List<com.lingolearnhub.og.entity.Word> getWords() {
        return words;
    }

    public String getName() {
        return name;
    }
}
