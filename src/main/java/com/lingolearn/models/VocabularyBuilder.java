package com.lingolearn.models;

import java.util.UUID;

public class VocabularyBuilder {
    private final VocabularySet root;

    private VocabularyBuilder(VocabularySet root) {
        this.root = root;
    }

    public static VocabularyBuilder create(String name, String description) {
        return new VocabularyBuilder(
                new VocabularySet(UUID.randomUUID(), name, description)
        );
    }

    public VocabularyBuilder addWord(String original, String translation) {
        Word word = new Word(UUID.randomUUID(), original, translation);
        root.addItem(word);
        return this;
    }

    public VocabularyBuilder addSet(String name, String description) {
        VocabularySet set = new VocabularySet(UUID.randomUUID(), name, description);
        root.addItem(set);
        return this;
    }

    public VocabularySet build() {
        return root;
    }
}
