package com.lingolearn.core.study;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.VocabularySetDTO;

class FlashcardModeImpl implements LingoLearn.StudyManager.FlashcardMode {
    @Override
    public LingoLearn.StudyManager.Session start(VocabularySetDTO set) {
        return new FlashcardSession(set);
    }
}
