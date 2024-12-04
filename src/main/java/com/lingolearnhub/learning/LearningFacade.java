package com.lingolearnhub.learning;

import com.lingolearnhub.model.UserProgress;
import com.lingolearnhub.model.VocabularySet;
import com.lingolearnhub.report.ReportController;

public class LearningFacade {
    private LearningController learningController;
    private ReportController reportController;

    public LearningFacade(LearningController learningController, ReportController reportController) {
        this.learningController = learningController;
        this.reportController = reportController;
    }

    public void startLearning(VocabularySet vocabularySet) {
        // Assume LearningController is set up to manage the learning session with a given VocabularySet
        learningController.setVocabularySet(vocabularySet);
        learningController.startLearning();
    }

    public void generateReport(UserProgress userProgress) {
        reportController.generateReport(userProgress);
    }
}
