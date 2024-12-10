package com.lingolearnhub.api;

import com.lingolearnhub.vocabulary.Word;

/**
 * Provides an API for managing vocabulary sets and their contents.
 * Developers can user this interface to create, update, delete and query vocabulary sets and words.
 */
public interface VocabularyService {

    /**
     * Creates a new vocabulary set.
     *
     * @param name The name of the vocabulary set (e.g., "English Basics").
     */
    void createVocabularySet(String name);

    /**
     * Add a word to an existing vocabulary set.
     *
     * @param vocabularySetName  The name of the target vocabulary set.
     * @param word               The {@link Word} object representing the word and its translation.
     */
    void addWordToVocabularySet(String vocabularySetName, Word word);

    /**
     * Removes an entire vocabulary set.
     *
     * @param name The name of the vocabulary set to remove.
     */
    void removeVocabularySet(String name);

    /**
     * Removes a specific word from a vocabulary set.
     *
     * @param vocabularySetName  The name of the target vocabulary set.
     * @param word               The word (as a {@link String}) to delete from vocabulary set.
     */
    void removeWordFromVocabularySet(String vocabularySetName, Word word);

}
