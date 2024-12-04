package com.og;

import com.lingolearnhub.og.LearningStrategy;
import com.lingolearnhub.og.LearningView;
import com.lingolearnhub.og.UserProgress;
import com.lingolearnhub.og.VocabularySet;

public class LearningController {
    private final LearningView view;
    private final UserProgress progress;
    private final VocabularySet vocabularySet;
    private LearningStrategy strategy;

    public LearningController(LearningView view, com.lingolearnhub.og.entity.VocabularySet vocabularySet, com.lingolearnhub.og.entity.UserProgress progress) {
        this.view = view;
        this.vocabularySet = vocabularySet;
        this.progress = progress;
    }

    public void setLearningStrategy(LearningStrategy strategy) {
        this.strategy = strategy;
    }

    public void startLearning() {
        view.displayVocabulary(vocabularySet);
        strategy.applyStrategy(vocabularySet, view, progress);
    }
}
