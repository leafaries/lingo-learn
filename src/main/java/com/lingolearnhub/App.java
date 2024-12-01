package com.lingolearnhub;

import com.lingolearnhub.controller.LearningController;
import com.lingolearnhub.controller.ReportController;
import com.lingolearnhub.entity.UserProgress;
import com.lingolearnhub.entity.VocabularySet;
import com.lingolearnhub.entity.Word;
import com.lingolearnhub.strategy.FlashcardStrategy;
import com.lingolearnhub.view.LearningView;
import com.lingolearnhub.view.ReportView;

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
