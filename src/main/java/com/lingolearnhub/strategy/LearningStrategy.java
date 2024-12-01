package com.lingolearnhub.strategy;

import com.lingolearnhub.view.LearningView;
import com.lingolearnhub.entity.UserProgress;
import com.lingolearnhub.entity.VocabularySet;

public interface LearningStrategy {
    void applyStrategy(VocabularySet vocabularySet, LearningView view, UserProgress progress);
}
