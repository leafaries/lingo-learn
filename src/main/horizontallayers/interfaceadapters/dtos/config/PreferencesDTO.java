package horizontallayers.interfaceadapters.dtos.config;

import horizontallayers.domain.enums.SessionType;

import java.time.Duration;
import java.util.Map;

/*
 - Represents current configuration state
 - No need for historical tracking
 - Single instance per user
 */
public record PreferencesDTO(
        SessionType defaultSessionType,
        Map<String, String> keyboardShortcuts,
        boolean soundEnabled,
        boolean darkMode,
        int wordsPerSession,
        Duration flashcardDisplayDuration,
        boolean showTranslationHints
) {
}
