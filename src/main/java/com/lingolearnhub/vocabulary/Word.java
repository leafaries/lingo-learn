package com.lingolearnhub.vocabulary;

/**
 * Represents a single word and its translation.
 */
public record Word(String word, String translation) implements VocabularyComponent {

    /**
     * Displays the word and its translation in the format "word - translation".
     */
    @Override
    public void display() {
        System.out.println(word + " - " + translation);
    }

}
