package com.lingolearnhub.learning;

import com.lingolearnhub.model.UserProgress;
import com.lingolearnhub.model.VocabularyComponent;
import com.lingolearnhub.model.VocabularySet;

public class LearningView {
    public void displayProgress(UserProgress progress) {
        System.out.println("Your current progress: " + progress.getProgress() + "%");
    }

    public void displayVocabulary(VocabularySet vocabularySet) {
        System.out.println("Vocabulary Set: " + vocabularySet.getName());
        for (VocabularyComponent vocabularyComponent : vocabularySet.getComponents()) {
            vocabularyComponent.display();
        }
    }

    public void displayChallenge(String challenge) {
        System.out.println("Challenge: " + challenge);
    }
}
