package com.og;

import com.og.ProgressObserver;
import com.og.VocabularySet;

public class LearningView implements ProgressObserver {
    @Override
    public void update(double progress) {
        System.out.println("Your current progress: " + progress + "%");
    }

    public void displayVocabulary(VocabularySet vocabularySet) {
        System.out.println("Vocabulary Set: " + vocabularySet.getName());
        for (var word : vocabularySet.getWords()) {
            System.out.println(word.word() + " - " + word.translation());
        }
    }

    public void displayChallenge(String challange) {
        System.out.println("Challange: " + challange);
    }
}
