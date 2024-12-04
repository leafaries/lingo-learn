package com.lingolearnhub.learning;

import com.lingolearnhub.learning.strategy.LearningStrategy;
import com.lingolearnhub.model.UserProgress;
import com.lingolearnhub.model.VocabularySet;

public class LearningController {
    private final LearningView view;
    private UserProgress progress;
    private VocabularySet vocabularySet;
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
        view.displayProgress(progress); // Call to display progress directly
    }

    public void setVocabularySet(VocabularySet vocabularySet) {
        this.vocabularySet = vocabularySet;
    }
}
