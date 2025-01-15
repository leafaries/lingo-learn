package com.lingolearn.core.study;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.config.PreferencesDTO;
import com.lingolearn.entities.PreferenceEntity;
import com.lingolearn.repos.PreferenceRepository;

class FlashcardModeImpl implements LingoLearn.StudyManager.FlashcardMode {
    private final PreferenceRepository preferenceRepository;

    public FlashcardModeImpl() {
        this.preferenceRepository = new PreferenceRepository();
    }

    @Override
    public LingoLearn.StudyManager.Session start(VocabularySetDTO set) {
        return new FlashcardSession(set);
    }

    @Override
    public PreferencesDTO getPreferences() {
        return preferenceRepository.findLatest()
                .map(this::mapToConfig)
                .orElse(new PreferencesDTO());
    }

    @Override
    public void updatePreferences(PreferencesDTO config) {
        PreferenceEntity entity = new PreferenceEntity();
        // Map config to entity
        preferenceRepository.save(entity);
    }

    private PreferencesDTO mapToConfig(PreferenceEntity entity) {
        return new PreferencesDTO(); // TODO: Implement mapping
    }
}
