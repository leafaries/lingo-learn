package com.lingolearnhub.vocabulary;

/**
 * Represents a single word and its translation in a vocabulary set.
 * This immutable class allows storing a word pair like "apple" (English) and "jabłko" (Polish).
 */
public record Word(String word, String translation) implements VocabularyComponent {

    /**
     * Displays the word and its translation.
     * E.g., "apple - jabłko".
     */
    @Override
    public void display() {
        System.out.println(word + " - " + translation);
    }

}
