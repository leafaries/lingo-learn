package com.lingolearn.core.study;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.entities.WordEntity;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;

class FlashcardSession implements LingoLearn.StudyManager.Session {
    public FlashcardSession(VocabularySetDTO set) {
        super(set, StudyMode.REGULAR, SessionType.FLASHCARD);
    }

    @Override
    protected boolean evaluateAnswer(WordEntity word, String answer) {
        return answer.trim().equalsIgnoreCase(word.getTranslation().trim());
    }
}
