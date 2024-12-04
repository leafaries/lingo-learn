package com.lingolearnhub.learning;

import com.lingolearnhub.learning.strategy.LearningStrategy;
import com.lingolearnhub.progress.UserProgress;
import com.lingolearnhub.progress.UserProgressService;
import com.lingolearnhub.vocabulary.VocabularySet;

public class LearningFacade {
    private final LearningStrategy strategy;
    private final UserProgressService progressService;

    public LearningFacade(LearningStrategy strategy, UserProgressService progressService) {
        this.strategy = strategy;
        this.progressService = progressService;
    }

    public void initializeLearningSession(VocabularySet vocabularySet, LearningView learningView, UserProgress userProgress) {
        strategy.applyLearning(vocabularySet, learningView, userProgress);
//        progressService.saveProgress(userProgress);
        System.out.println("Learning session completed and progress saved");
    }
}

/* Example usage:
    public static void main(String[] args) {
        LearningStrategy strategy = new FlashcardStrategy();
        UserProgressService progressService = new UserProgressService();
        LearningFacade facade = new LearningFacade(strategy, progressService);

        VocabularySet vocabularySet = new VocabularySet();
        LearningView view = new LearningView();
        UserProgress progress = new UserProgress();

        facade.initiateLearningSession(vocabularySet, view, progress);
    }
 */

// old code for deletion
//    private LearningController learningController;
//    private ReportController reportController;
//
//    public LearningFacade(LearningController learningController, ReportController reportController) {
//        this.learningController = learningController;
//        this.reportController = reportController;
//    }
//
//    public void startLearning(VocabularySet vocabularySet) {
//        // Assume LearningController is set up to manage the learning session with a given VocabularySet
//        learningController.setVocabularySet(vocabularySet);
//        learningController.startLearning();
//    }
//
//    public void generateReport(UserProgress userProgress) {
//        reportController.generateReport(userProgress);
//    }
