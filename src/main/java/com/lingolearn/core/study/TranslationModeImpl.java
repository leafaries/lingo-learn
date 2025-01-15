package com.lingolearn.core.study;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;

class TranslationModeImpl implements LingoLearn.StudyManager.TranslationMode {
    @Override
    public LingoLearn.StudyManager.Session start(VocabularySetDTO set) {
        return new StudySessionImpl(
                set,
                SessionType.TRANSLATION_LOGIC,
                StudyMode.REGULAR
        );
    }
}
