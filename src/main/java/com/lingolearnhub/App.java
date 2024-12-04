package com.lingolearnhub;

import com.lingolearnhub.learning.LearningController;
import com.lingolearnhub.learning.LearningView;
import com.lingolearnhub.learning.strategy.FlashcardStrategy;
import com.lingolearnhub.model.UserProgress;
import com.lingolearnhub.model.VocabularySet;
import com.lingolearnhub.model.Word;

public class App {
    public static void main(String[] args) {
        VocabularySet vocabularySet = new VocabularySet("English Vocabulary");
        vocabularySet.addWord(new Word("hello", "cześć"));
        vocabularySet.addWord(new Word("apple", "jabłko"));

        LearningView learningView = new LearningView();
        UserProgress userProgress = new UserProgress();

        LearningController learningController = new LearningController(learningView, vocabularySet, userProgress);
        learningController.setLearningStrategy(new FlashcardStrategy()); // Or MultipleChoiceStrategy
        learningController.startLearning();

        ReportView reportView = new ReportView();
        ReportController reportController = new ReportController(reportView, userProgress);
        reportController.showReport();
    }
}
