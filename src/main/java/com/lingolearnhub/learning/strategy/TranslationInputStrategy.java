package com.lingolearnhub.learning.strategy;

import com.lingolearnhub.learning.LearningView;
import com.lingolearnhub.progress.UserProgress;
import com.lingolearnhub.vocabulary.VocabularyComponent;
import com.lingolearnhub.vocabulary.VocabularySet;
import com.lingolearnhub.vocabulary.Word;

public class TranslationInputStrategy implements LearningStrategy {
    @Override
    public void applyLearning(VocabularySet vocabularySet, LearningView learningView, UserProgress userProgress) {
        for (VocabularyComponent component : vocabularySet.getComponents()) {
            if (component instanceof Word) {
//                Word word = (Word) component;
//                // Present a word and ask the user to input its translation
//                String userTranslation = learningView.askUser("Translate: " + word.word());
//
//                // Verify the user's input with the correct translation
//                if (word.translation().equalsIgnoreCase(userTranslation)) {
//                    learningView.displayMessage("Correct!");
//                    userProgress.increaseScore(); // Example method to increase score
//                } else {
//                    learningView.displayMessage("Incorrect. Correct answer: " + word.translation());
//                }
            }
        }
        // Optionally update or display user progress at the end
        learningView.displayProgress(userProgress);
    }
}
