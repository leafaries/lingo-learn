package com.lingolearn.core.study;

import com.lingolearn.core.LingoLearn;

public class StudyManagerImpl implements LingoLearn.StudyManager {
    private final FlashcardMode flashcardMode;
    private final TranslationMode translationMode;
    private final ManualMode manualMode;

    public StudyManagerImpl() {
        this.flashcardMode = new FlashcardModeImpl();
        this.translationMode = new TranslationModeImpl();
        this.manualMode = new ManualModeImpl();
    }

    @Override
    public FlashcardMode flashcards() {
        return flashcardMode;
    }

    @Override
    public TranslationMode translation() {
        return translationMode;
    }

    @Override
    public ManualMode manual() {
        return manualMode;
    }
}
