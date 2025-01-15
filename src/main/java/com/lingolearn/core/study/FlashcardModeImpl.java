package com.lingolearn.core.study;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.config.PreferenceConfigDTO;
import com.lingolearn.entities.PreferenceEntity;
import com.lingolearn.repo.PreferenceRepository;

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
    public PreferenceConfigDTO getPreferences() {
        return preferenceRepository.findLatest()
                .map(this::mapToConfig)
                .orElse(new PreferenceConfigDTO());
    }

    @Override
    public void updatePreferences(PreferenceConfigDTO config) {
        PreferenceEntity entity = new PreferenceEntity();
        // Map config to entity
        preferenceRepository.save(entity);
    }

    private PreferenceConfigDTO mapToConfig(PreferenceEntity entity) {
        return new PreferenceConfigDTO(); // TODO: Implement mapping
    }
}
