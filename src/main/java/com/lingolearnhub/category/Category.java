package com.lingolearnhub.category;

import com.lingolearnhub.model.VocabularySet;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<VocabularySet> vocabularySets = new ArrayList<>();

    public Category(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VocabularySet> getVocabularySets() {
        return vocabularySets;
    }

    public void setVocabularySets(List<VocabularySet> vocabularySets) {
        this.vocabularySets = vocabularySets;
    }
}
