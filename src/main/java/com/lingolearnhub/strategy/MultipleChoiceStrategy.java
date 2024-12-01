package com.lingolearnhub.strategy;

import com.lingolearnhub.view.LearningView;
import com.lingolearnhub.entity.UserProgress;
import com.lingolearnhub.entity.VocabularySet;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MultipleChoiceStrategy implements LearningStrategy {
    @Override
    public void applyStrategy(VocabularySet vocabularySet, LearningView view, UserProgress progress) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        for (var word : vocabularySet.getWords()) {
            String correctAnswer = word.translation();
            List<String> options = List.of(correctAnswer, "wrong1", "wrong2", "wrong3");
            // Shuffle options and present to the user
            System.out.println("Which of these is the translation of: " + word.word());
            view.displayChallenge("What is the translation of: " + word.word());
            int i = 1;
            for (String option : options) {
                System.out.println(i++ + ". " + option);
            }
            int userChoice = scanner.nextInt();
            if (options.get(userChoice - 1).equals(correctAnswer)) {
                progress.incrementCorrect();
            }
        }
    }
}
