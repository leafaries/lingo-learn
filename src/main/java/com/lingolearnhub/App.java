package com.lingolearnhub;

import com.lingolearnhub.learning.LearningController;
import com.lingolearnhub.learning.LearningFacade;
import com.lingolearnhub.learning.LearningFacadeImplementation;
import com.lingolearnhub.learning.LearningView;
import com.lingolearnhub.learning.strategy.FlashcardStrategy;
import com.lingolearnhub.learning.strategy.factories.FlashcardStrategyFactory;
import com.lingolearnhub.progress.UserProgress;
import com.lingolearnhub.progress.UserProgressService;
import com.lingolearnhub.vocabulary.VocabularySet;
import com.lingolearnhub.vocabulary.Word;

public class App {

    // TODO: - Change all comments to english
    //       - Delete dead code
    //       - Use Lombok wherever appropiate
    //       - Update README.md
    //       - Specify what API EXACTLY this library will be sharing
    //       - Add tests as soon as you're sure you've specified some class
    //       - Compete TODOs
    //       - Investigate "Potential Over-Engineering" (1)
    //       - Add proper error handling
    //       - Unstube UserProgress
    //       Not that important at the beginning:
    //       - Add logging
    //       - Write documentation (JavaDocs and more)

    // NOTE:

    public static void main(String[] args) {
        LearningController learningController = getLearningController();
        learningController.startLearning();

        LearningFacade learningFacade = new LearningFacadeImplementation(
                new FlashcardStrategyFactory().createStrategy(),
                new UserProgressService()
        );

//        ReportView reportView = new ReportView();
//        ReportController reportController = new ReportController(reportView, userProgress);
//        reportController.showReport();
    }

    private static LearningController getLearningController() {
        VocabularySet vocabularySet = new VocabularySet("English Vocabulary");
        vocabularySet.addWord(new Word("hello", "cześć"));
        vocabularySet.addWord(new Word("apple", "jabłko"));

        LearningView learningView = new LearningView();
        UserProgress userProgress = new UserProgress();

        LearningController learningController = new LearningController(learningView, vocabularySet, userProgress);
        learningController.setLearningStrategy(new FlashcardStrategy()); // Or MultipleChoiceStrategy
        return learningController;
    }

}
