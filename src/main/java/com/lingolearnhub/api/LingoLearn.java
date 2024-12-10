package com.lingolearnhub.api;

import com.lingolearnhub.progress.ChallengeStatistics;
import com.lingolearnhub.vocabulary.VocabularySet;

import java.util.List;

// TODO: - Implement methods
//       - Make sure the JavaDoc is correct in every way
/**
 * The main entry point for the Lingolearn library.
 * Provides methods to configure, start learning sessions, and manage user progress.
 * This class abstracts away internal implementation details and offers a clean API for developers.
 */
public class LingoLearn {

    /**
     * Configures the library with custom behavior or settings such as learning strategies or daily limits.
     *
     * @param config A {@link Configuration} object containing settings for the learning environment.
     *               For example, specify the learning strategy as "flashcard" and challenge limits.
     */
    public void configure(Configuration config) { }

    /**
     * Starts a learning session for a given user with a particular vocabulary set.
     * This initializes the learning environment using the provided configuration and track user progress.
     *
     * @param userId         The unique identifier for the user starting the session.
     * @param vocabularySet  The {@link VocabularySet} containing the words and challenges to be learned.
     *                       This enables the user to engage with structured vocabulary items.
     */
    public void startLearning(String userId, VocabularySet vocabularySet) { }

    /**
     * Fetches the learning progress statistics for a specific user.
     * Statistics include details about completed challenges, total challenges, and success rate.
     *
     * @param userId  The user's unique identifier.
     * @return        A {@link ChallengeStatistics} object containing insights into the user's performance.
     */
    public ChallengeStatistics getUserStatistics(String userId) { return null; }

    /**
     * Retrieves a list of daily challenges that the user can complete.
     * These challenges are preconfigured based on the current learning strategy and user progress.
     *
     * @param userId  The user's unique identifier.
     * @return        A {@link List} of challenge descriptions (e.g., "Vocabulary Quiz", "Grammar Puzzle").
     */
    public List<String> getDailyChallenges(String userId) { return List.of(); }

    /**
     * Generates a progress report for the specified user.
     * The report contains textual data about the user's current progress, completed challenges,
     * and learning milestones.
     *
     * @param userId  The user's unique identifier.
     * @return        A {@link String} representation of the user's progress (e.g., "Your progress: 75%").
     */
    public String generateProgressReport(String userId) { return ""; }

}
