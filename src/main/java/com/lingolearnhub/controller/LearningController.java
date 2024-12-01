package com.lingolearnhub.controller;

import com.lingolearnhub.strategy.LearningStrategy;
import com.lingolearnhub.view.LearningView;
import com.lingolearnhub.entity.UserProgress;
import com.lingolearnhub.entity.VocabularySet;

public class LearningController {
    private final LearningView view;
    private final UserProgress progress;
    private final VocabularySet vocabularySet;
    private LearningStrategy strategy;

    public LearningController(LearningView view, VocabularySet vocabularySet, UserProgress progress) {
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
