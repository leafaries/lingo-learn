package com.lingolearn.dtos.config;

import com.lingolearn.enums.SessionType;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/*
 - Represents current configuration state
 - No need for historical tracking
 - Single instance per user
 */
public record PreferenceConfigDTO(
        SessionType defaultSessionType,
        Map<String, String> keyboardShortcuts,
        boolean soundEnabled,
        boolean darkMode,
        Duration flashcardDisplayDuration,
        int wordsPerSession,
        boolean showTranslationHints
) {
    // Compact constructor for validation
    public PreferenceConfigDTO {
        // Defensive copy of mutable map
        keyboardShortcuts = new HashMap<>(keyboardShortcuts);

        // Validate wordsPerSession
        if (wordsPerSession <= 0) {
            throw new IllegalArgumentException("Words per session must be positive");
        }

        // Validate flashcardDisplayDuration
        if (flashcardDisplayDuration.isNegative()) {
            throw new IllegalArgumentException("Display duration cannot be negative");
        }
    }

    // Factory method for default configuration
    public static PreferenceConfigDTO createDefault() {
        return new PreferenceConfigDTO(
                SessionType.FLASHCARD,
                createDefaultShortcuts(),
                true,  // soundEnabled
                false, // darkMode
                Duration.ofSeconds(5),
                20,    // wordsPerSession
                true   // showTranslationHints
        );
    }

    private static Map<String, String> createDefaultShortcuts() {
        Map<String, String> shortcuts = new HashMap<>();
        shortcuts.put("nextWord", "N");
        shortcuts.put("previousWord", "P");
        shortcuts.put("showAnswer", "Space");
        shortcuts.put("markCorrect", "C");
        shortcuts.put("markIncorrect", "I");
        return shortcuts;
    }

    // Override the map getter to return a defensive copy
    @Override
    public Map<String, String> keyboardShortcuts() {
        return new HashMap<>(keyboardShortcuts);
    }

    // Convenience methods for creating modified copies
    public PreferenceConfigDTO withDefaultSessionType(SessionType newType) {
        return new PreferenceConfigDTO(newType, keyboardShortcuts, soundEnabled,
                darkMode, flashcardDisplayDuration, wordsPerSession, showTranslationHints);
    }

    public PreferenceConfigDTO withKeyboardShortcuts(Map<String, String> newShortcuts) {
        return new PreferenceConfigDTO(defaultSessionType, newShortcuts, soundEnabled,
                darkMode, flashcardDisplayDuration, wordsPerSession, showTranslationHints);
    }

    public PreferenceConfigDTO withWordsPerSession(int newWordsPerSession) {
        return new PreferenceConfigDTO(defaultSessionType, keyboardShortcuts, soundEnabled,
                darkMode, flashcardDisplayDuration, newWordsPerSession, showTranslationHints);
    }

    public PreferenceConfigDTO withFlashcardDisplayDuration(Duration newDuration) {
        return new PreferenceConfigDTO(defaultSessionType, keyboardShortcuts, soundEnabled,
                darkMode, newDuration, wordsPerSession, showTranslationHints);
    }
}
