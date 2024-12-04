package com.lingolearnhub.learning.strategy;

import com.lingolearnhub.learning.LearningView;
import com.lingolearnhub.model.UserProgress;
import com.lingolearnhub.model.VocabularyComponent;
import com.lingolearnhub.model.VocabularySet;
import com.lingolearnhub.model.Word;

public class TranslationInputStrategy implements LearningStrategy {
    @Override
    public void applyLearning(VocabularySet vocabularySet, LearningView learningView, UserProgress userProgress) {
        for (VocabularyComponent component : vocabularySet.getComponents()) {
            if (component instanceof Word) {
                Word word = (Word) component;
                // Present a word and ask the user to input its translation
                String userTranslation = learningView.askUser("Translate: " + word.getWord());

                // Verify the user's input with the correct translation
                if (word.getTranslation().equalsIgnoreCase(userTranslation)) {
                    learningView.displayMessage("Correct!");
                    userProgress.increaseScore(); // Example method to increase score
                } else {
                    learningView.displayMessage("Incorrect. Correct answer: " + word.getTranslation());
                }
            }
        }
        // Optionally update or display user progress at the end
        learningView.displayProgress(userProgress);
    }
}
