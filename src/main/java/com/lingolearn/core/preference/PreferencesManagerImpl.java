package com.lingolearn.core.preference;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.core.mapping.LingoLearnMapper;
import com.lingolearn.dtos.config.PreferencesDTO;
import com.lingolearn.entities.PreferenceEntity;
import com.lingolearn.enums.SessionType;
import com.lingolearn.repos.PreferenceRepository;

import java.time.Duration;
import java.util.Map;

public class PreferencesManagerImpl implements LingoLearn.PreferencesManager {
    private final PreferenceRepository repository;
    private final LingoLearnMapper mapper = LingoLearnMapper.INSTANCE;

    public PreferencesManagerImpl() {
        this.repository = new PreferenceRepository();
    }

    @Override
    public PreferencesDTO getPreferences() {
        return repository.findLatest()
                .map(mapper::toDTO)
                .orElseGet(this::createDefaultPreferences);
    }

    @Override
    public void updatePreferences(PreferencesDTO config) {
        PreferenceEntity entity = repository.findLatest()
                .orElse(new PreferenceEntity());

        mapper.updatePreferenceEntity(config, entity);
        repository.save(entity);
    }

    private PreferencesDTO createDefaultPreferences() {
        return new PreferencesDTO(
                SessionType.FLASHCARD,
                getDefaultKeyboardShortcuts(),
                true, // Sound enabled
                false, // Dark mode disabled
                20, // Default words per session
                Duration.ofSeconds(3), // Default display duration
                true // Show hints enabled
        );
    }

    private Map<String, String> getDefaultKeyboardShortcuts() {
        return Map.of(
                "nextWord", "N",
                "previousWord", "P",
                "showAnswer", "Space",
                "submit", "Enter",
                "skip", "Tab",
                "showHint", "H"
        );
    }
}
