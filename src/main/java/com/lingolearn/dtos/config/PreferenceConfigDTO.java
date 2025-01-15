package com.lingolearn.dtos.config;

import com.lingolearn.enums.SessionType;

import java.time.Duration;
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

}
