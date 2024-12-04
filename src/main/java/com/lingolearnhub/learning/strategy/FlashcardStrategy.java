package com.lingolearnhub.learning.strategy;

import com.lingolearnhub.learning.LearningView;
import com.lingolearnhub.model.UserProgress;
import com.lingolearnhub.model.VocabularyComponent;
import com.lingolearnhub.model.VocabularySet;
import com.lingolearnhub.model.Word;

public class FlashcardStrategy implements LearningStrategy {
    @Override
    public void applyLearning(VocabularySet vocabularySet, LearningView learningView, UserProgress userProgress) {
        for (VocabularyComponent component : vocabularySet.getComponents()) {
            if (component instanceof Word) {
                // Simulate a flashcard interaction
                Word word = (Word) component;
                learningView.displayChallenge("Translate: " + word.word());
                // Possible logic to process user input or check correctness
            }
        }
        // Update user progress after flashcard session
        userProgress.updateProgress( /* some logic to calculate progress */);
    }
}
