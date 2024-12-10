package com.lingolearnhub.learning;

import com.lingolearnhub.learning.strategy.LearningStrategy;
import com.lingolearnhub.progress.UserProgress;
import com.lingolearnhub.vocabulary.VocabularySet;

public class LearningController {

    private final LearningView view;
    private final UserProgress progress;
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
        strategy.applyLearning(vocabularySet, view, progress);
        view.displayProgress(progress); // Call to display progress directly
    }

    public void setVocabularySet(VocabularySet vocabularySet) {
        this.vocabularySet = vocabularySet;
    }

}
