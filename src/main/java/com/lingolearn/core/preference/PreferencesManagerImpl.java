package com.lingolearn.core.preference;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.config.PreferencesDTO;
import com.lingolearn.entities.PreferenceEntity;
import com.lingolearn.enums.SessionType;
import com.lingolearn.repos.PreferenceRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PreferencesManagerImpl implements LingoLearn.PreferencesManager {
    private final PreferenceRepository repository;

    public PreferencesManagerImpl() {
        this.repository = new PreferenceRepository();
    }

    @Override
    public PreferencesDTO getPreferences() {
        Optional<PreferenceEntity> preferences = repository.findLatest();

        if (preferences.isEmpty()) {
            // Create default preferences if none exist
            return createDefaultPreferences();
        }

        return mapEntityToConfig(preferences.get());
    }

    @Override
    public void updatePreferences(PreferencesDTO config) {
        PreferenceEntity entity = repository.findLatest()
                .orElse(new PreferenceEntity());

        updateEntityFromConfig(entity, config);
        repository.save(entity);
    }

    private PreferencesDTO createDefaultPreferences() {
        PreferenceEntity entity = new PreferenceEntity();

        // Set default keyboard shortcuts
        Map<String, String> defaultShortcuts = new HashMap<>();
        defaultShortcuts.put("nextWord", "N");
        defaultShortcuts.put("previousWord", "P");
        defaultShortcuts.put("showAnswer", "Space");
        defaultShortcuts.put("markCorrect", "C");
        defaultShortcuts.put("markIncorrect", "I");
        entity.setKeyboardShortcuts(defaultShortcuts);

        // Set other defaults
        entity.setDefaultSessionType(SessionType.FLASHCARD);
        entity.setSoundEnabled(true);
        entity.setDarkMode(false);
        entity.setLastModified(LocalDateTime.now());

        // Save and return
        repository.save(entity);
        return mapEntityToConfig(entity);
    }

    private PreferencesDTO mapEntityToConfig(PreferenceEntity entity) {
        return PreferencesDTO.builder()
                .keyboardShortcuts(new HashMap<>(entity.getKeyboardShortcuts()))
                .defaultSessionType(entity.getDefaultSessionType())
                .soundEnabled(entity.isSoundEnabled())
                .darkMode(entity.isDarkMode())
                .build();
    }

    private void updateEntityFromConfig(PreferenceEntity entity, PreferencesDTO config) {
        // Update keyboard shortcuts
        if (config.getKeyboardShortcuts() != null) {
            entity.setKeyboardShortcuts(new HashMap<>(config.getKeyboardShortcuts()));
        }

        // Update session type if provided
        if (config.getDefaultSessionType() != null) {
            entity.setDefaultSessionType(config.getDefaultSessionType());
        }

        // Update boolean preferences
        entity.setSoundEnabled(config.isSoundEnabled());
        entity.setDarkMode(config.isDarkMode());

        // Update modification timestamp
        entity.setLastModified(LocalDateTime.now());
    }

    /**
     * Updates a single keyboard shortcut
     */
    public void updateKeyboardShortcut(String action, String shortcut) {
        PreferenceEntity entity = repository.findLatest()
                .orElse(new PreferenceEntity());

        Map<String, String> shortcuts = new HashMap<>(entity.getKeyboardShortcuts());
        shortcuts.put(action, shortcut);
        entity.setKeyboardShortcuts(shortcuts);
        entity.setLastModified(LocalDateTime.now());

        repository.save(entity);
    }

    /**
     * Resets preferences to default values
     */
    public void resetToDefaults() {
        PreferenceEntity entity = repository.findLatest()
                .orElse(new PreferenceEntity());

        repository.delete(entity);
        createDefaultPreferences();
    }

    /**
     * Gets a specific keyboard shortcut
     */
    public String getKeyboardShortcut(String action) {
        Optional<PreferenceEntity> preferences = repository.findLatest();

        if (preferences.isEmpty()) {
            return getDefaultShortcut(action);
        }

        return preferences.get().getKeyboardShortcuts()
                .getOrDefault(action, getDefaultShortcut(action));
    }

    private String getDefaultShortcut(String action) {
        Map<String, String> defaults = new HashMap<>();
        defaults.put("nextWord", "N");
        defaults.put("previousWord", "P");
        defaults.put("showAnswer", "Space");
        defaults.put("markCorrect", "C");
        defaults.put("markIncorrect", "I");

        return defaults.getOrDefault(action, "");
    }

    /**
     * Validates keyboard shortcut configuration
     */
    public boolean validateShortcuts(Map<String, String> shortcuts) {
        // Check for null or empty values
        if (shortcuts == null || shortcuts.isEmpty()) {
            return false;
        }

        // Check for duplicate shortcuts
        long uniqueShortcuts = shortcuts.values().stream()
                .distinct()
                .count();

        return uniqueShortcuts == shortcuts.size();
    }
}
