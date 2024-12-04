package com.lingolearnhub.vocabulary;

import java.util.ArrayList;
import java.util.List;

/**
 * Przechowuje zestaw słówek.
 */
public class VocabularySet implements VocabularyComponent {
    private final String name;
    private final List<VocabularyComponent> components;

    public VocabularySet(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    public void addWord(Word word) {
        components.add(word);
    }

    public List<VocabularyComponent> getComponents() {
        return components;
    }

    public String getName() {
        return name;
    }

    @Override
    public void display() {
        System.out.println("Vocabulary Set: " + name);
        for (VocabularyComponent component : components) {
            component.display();
        }
    }
}
