package com.lingolearn.models;

public class VocabularyBuilder {
    private final VocabularySet root;

    private VocabularyBuilder(VocabularySet root) {
        this.root = root;
    }

    public static VocabularyBuilder create(String name, String description) {
        return new VocabularyBuilder(
                new VocabularySet(name, description)
        );
    }

    public VocabularyBuilder addWord(String original, String translation) {
        Word word = new Word(original, translation);
        root.addItem(word);
        return this;
    }

    public VocabularyBuilder addSet(String name, String description) {
        VocabularySet set = new VocabularySet(name, description);
        root.addItem(set);
        return this;
    }

    public VocabularySet build() {
        return root;
    }
}
