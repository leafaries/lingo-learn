package com.lingolearnhub.vocabulary;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
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

    @Override
    public void display() {
        System.out.println("Vocabulary Set: " + name);
        for (VocabularyComponent component : components) {
            component.display();
        }
    }

}
