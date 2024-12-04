package com.og;

import com.lingolearnhub.og.LearningController;
import com.lingolearnhub.og.ReportController;
import com.lingolearnhub.og.UserProgress;
import com.lingolearnhub.og.VocabularySet;
import com.lingolearnhub.og.Word;

public class App {
    public static void main(String[] args) {
        VocabularySet vocabularySet = new VocabularySet("English Vocabulary");
        vocabularySet.addWord(new com.lingolearnhub.og.Word("hello", "cześć"));
        vocabularySet.addWord(new Word("apple", "jabłko"));

        com.lingolearnhub.og.LearningView learningView = new com.lingolearnhub.og.LearningView();
        UserProgress userProgress = new UserProgress();

        LearningController learningController = new LearningController(learningView, vocabularySet, userProgress);
        learningController.setLearningStrategy(new com.lingolearnhub.og.FlashcardStrategy()); // Or MultipleChoiceStrategy
        learningController.startLearning();

        com.lingolearnhub.og.ReportView reportView = new com.lingolearnhub.og.ReportView();
        ReportController reportController = new ReportController(reportView, userProgress);
        reportController.showReport();
    }
}
