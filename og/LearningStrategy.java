package com.og;

import com.lingolearnhub.og.LearningView;
import com.lingolearnhub.og.entity.UserProgress;
import com.lingolearnhub.og.entity.VocabularySet;

public interface LearningStrategy {
    void applyStrategy(VocabularySet vocabularySet, LearningView view, UserProgress progress);
}
