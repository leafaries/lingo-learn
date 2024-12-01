package com.lingolearnhub.strategy;

import com.lingolearnhub.view.LearningView;
import com.lingolearnhub.entity.UserProgress;
import com.lingolearnhub.entity.VocabularySet;

import java.util.Scanner;

public class FlashcardStrategy implements LearningStrategy {
    @Override
    public void applyStrategy(VocabularySet vocabularySet, LearningView view, UserProgress progress) {
        Scanner scanner = new Scanner(System.in);
        for (var word : vocabularySet.getWords()) {
            view.displayChallenge("What is the translation of: " + word.word());
            String answer = scanner.nextLine();
            if (answer.equals(word.translation())) {
                progress.incrementCorrect();
            }
            progress.incrementTotal();
        }
    }
}
