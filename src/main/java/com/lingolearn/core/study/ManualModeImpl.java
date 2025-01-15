package com.lingolearn.core.study;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;

class ManualModeImpl implements LingoLearn.StudyManager.ManualMode {
    @Override
    public LingoLearn.StudyManager.Session start(VocabularySetDTO set) {
        return new StudySessionImpl(
                set,
                SessionType.MANUAL_TRANSLATION,
                StudyMode.REGULAR
        );
    }
}
