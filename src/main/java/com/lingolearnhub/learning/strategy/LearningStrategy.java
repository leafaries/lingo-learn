package com.lingolearnhub.learning.strategy;

import com.lingolearnhub.learning.LearningView;
import com.lingolearnhub.progress.UserProgress;
import com.lingolearnhub.vocabulary.VocabularySet;

public interface LearningStrategy {
    void applyLearning(VocabularySet vocabularySet, LearningView learningView, UserProgress userProgress);
}
