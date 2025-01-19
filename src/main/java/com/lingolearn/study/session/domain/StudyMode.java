package com.lingolearn.study.session.domain;

import com.lingolearn.persistence.settings.domain.StudyPreferences;

/**
 * Represents different modes of studying vocabulary in the application.
 * Each mode offers a different learning approach and interaction style.
 */
public enum StudyMode {
    FLASHCARD(true, false),        // Show word/translation with manual reveal
    MULTIPLE_CHOICE(false, true),  // Choose correct translation from options
    MANUAL_INPUT(false, true),     // Type in the translation manually
    MIXED(true, true);             // Combination of different modes

    private final boolean allowsConfidenceRating;
    private final boolean requiresAnswerValidation;

    StudyMode(boolean allowsConfidenceRating, boolean requiresAnswerValidation) {
        this.allowsConfidenceRating = allowsConfidenceRating;
        this.requiresAnswerValidation = requiresAnswerValidation;
    }

    public boolean allowsConfidenceRating() {
        return allowsConfidenceRating;
    }

    public boolean requiresAnswerValidation() {
        return requiresAnswerValidation;
    }

    /**
     * Returns appropriate next mode based on user's performance and preferences.
     * For example, if user is doing well in MULTIPLE_CHOICE, might suggest MANUAL_INPUT.
     */
    public StudyMode suggestNextMode(double currentAccuracy, StudyPreferences preferences) {
        if (currentAccuracy < 0.7) {
            return MULTIPLE_CHOICE; // Fall back to easier mode
        }

        return switch (this) {
            case FLASHCARD -> MULTIPLE_CHOICE;
            case MULTIPLE_CHOICE -> currentAccuracy > 0.9 ? MANUAL_INPUT : MULTIPLE_CHOICE;
            case MANUAL_INPUT -> currentAccuracy > 0.95 ? MIXED : MANUAL_INPUT;
            case MIXED -> MIXED;
        };
    }

    /**
     * Returns default session duration in minutes for this mode
     */
    public int getDefaultSessionDuration() {
        return switch (this) {
            case FLASHCARD -> 10;
            case MULTIPLE_CHOICE -> 15;
            case MANUAL_INPUT -> 20;
            case MIXED -> 25;
        };
    }
}
