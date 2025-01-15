package com.lingolearn.core;

import com.lingolearn.dtos.StudyResultDTO;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.study.StudyProgressDTO;

/**
 * Represents an active study session.
 * A session manages the flow of words, tracks progress, and handles user
 * answers.
 */
public interface Session {
    /**
     * Gets the current word being studied.
     * Returns null if the session is complete.
     *
     * @return the current WordDTO or null if no more words
     */
    WordDTO getCurrentWord();

    /**
     * Submits an answer for the current word and advances to the next word.
     * This method should validate the answer and update session statistics.
     *
     * @param answer the user's answer for the current word
     * @return progress information including correctness and overall statistics
     * @throws IllegalStateException if there is no current word (session is complete)
     */
    StudyProgressDTO submitAnswer(String answer);

    /**
     * Completes the session, calculating final statistics and marks it as finished.
     * This should be called when all words have been processed or when ending early.
     *
     * @return final study results including statistics and problematic words
     */
    StudyResultDTO complete();

    /**
     * Abandons the current session without completing it.
     * This should be called when the user wants to exit without finishing.
     */
    void abandon();
}
