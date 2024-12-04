package com.lingolearnhub.vocabulary;

/**
 * Reprezentuje pojedyncze słowo i jego tłumaczenie.
 */
public record Word(String word, String translation) implements VocabularyComponent {
    @Override
    public void display() {
        System.out.println(word + " - " + translation);
    }
}
