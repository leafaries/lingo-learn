package com.lingolearnhub.learning.strategy;

import com.lingolearnhub.learning.LearningView;
import com.lingolearnhub.model.UserProgress;
import com.lingolearnhub.model.VocabularySet;

public interface LearningStrategy {
    void applyLearning(VocabularySet vocabularySet, LearningView learningView, UserProgress userProgress);
}
