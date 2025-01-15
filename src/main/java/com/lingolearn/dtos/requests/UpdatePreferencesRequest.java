package com.lingolearn.dtos.requests;

import com.lingolearn.enums.SessionType;

import java.time.Duration;
import java.util.Map;

public record UpdatePreferencesRequest(
        SessionType defaultSessionType,
        Map<String, String> keyboardShortcuts,
        boolean soundEnabled,
        boolean darkMode,
        int wordsPerSession,
        Duration flashcardDisplayDuration,
        boolean showTranslationHints) {
}
