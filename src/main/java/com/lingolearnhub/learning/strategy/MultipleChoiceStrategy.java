package com.lingolearnhub.learning.strategy;

import com.lingolearnhub.learning.LearningView;
import com.lingolearnhub.progress.UserProgress;
import com.lingolearnhub.vocabulary.VocabularyComponent;
import com.lingolearnhub.vocabulary.VocabularySet;
import com.lingolearnhub.vocabulary.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultipleChoiceStrategy implements LearningStrategy {
    @Override
    public void applyLearning(VocabularySet vocabularySet, LearningView learningView, UserProgress userProgress) {
        List<Word> words = extractWords(vocabularySet);

        for (Word word : words) {
            // Prepare multiple choice options, including the correct one
            List<String> options = generateOptions(word, words);
            learningView.displayChallenge("What is the translation of: " + word.word() + "? Choices: " + String.join(", ", options));

            // Logic to process user input and check correctness (omitted for brevity)
        }
        // Update user progress after multiple choice session
//        userProgress.updateProgress(/* some logic to calculate progress */);
    }

    private List<String> generateOptions(Word correctWord, List<Word> allWords) {
        List<String> options = new ArrayList<>();
        options.add(correctWord.translation());

        while (options.size() < 4) { // Assuming 4 options per question
            Word randomWord = allWords.get((int) (Math.random() * allWords.size()));
            if (!options.contains(randomWord.translation())) {
                options.add(randomWord.translation());
            }
        }

        Collections.shuffle(options); // Shuffle options for randomness
        return options;
    }

    private List<Word> extractWords(VocabularySet vocabularySet) {
        List<Word> words = new ArrayList<>();

        for (VocabularyComponent component : vocabularySet.getComponents()) {
            if (component instanceof Word) {
                words.add((Word) component);
            }
        }

        return words;
    }
}

