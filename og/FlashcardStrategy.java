package com.og;

import com.lingolearnhub.og.LearningStrategy;
import com.lingolearnhub.og.LearningView;
import com.lingolearnhub.og.entity.UserProgress;
import com.lingolearnhub.og.entity.VocabularySet;

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
