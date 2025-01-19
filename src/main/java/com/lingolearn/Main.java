package com.lingolearn;

import com.lingolearn.presententatioofthepubsub.SomeRandomViewModel;
import com.lingolearn.presententatioofthepubsub.View;
import com.lingolearn.vocabulary.word.adapter.WordController;
import com.lingolearn.vocabulary.word.adapter.WordPresenter;
import com.lingolearn.vocabulary.word.adapter.WordViewModel;
import com.lingolearn.vocabulary.word.adapter.dtos.CreateWordDTO;
import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.infra.WordDatabaseImpl;
import com.lingolearn.vocabulary.word.infra.WordRepositoryImpl;
import com.lingolearn.vocabulary.word.infra.ui.WordView;
import com.lingolearn.vocabulary.word.usecase.WordInteractor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var vm = new SomeRandomViewModel();
        var v = new View(vm);
        vm.mainBusinessLogic();

        // Testing of word functionality
        var wordDatabase = new WordDatabaseImpl();
        var wordRepository = new WordRepositoryImpl(wordDatabase);

        var wordViewModel = new WordViewModel();
        var wordPresenter = new WordPresenter(wordViewModel);

        var wordInteractor = new WordInteractor(wordPresenter, wordRepository);
        var wordController = new WordController(wordInteractor);
        var wordView = new WordView(wordController, wordViewModel);
        wordView.createNewWord(new CreateWordDTO(
                "original",
                "translation",
                "partOfSpeech",
                List.of("123", "2222"),
                Difficulty.HARD
        ));

        // some logic

//        // 1. Create a vocabulary set using SetCreator
//        VocabularySetDTO foodSet = new LingoLearnApp.VocabularyManager.SetCreator("Food", "Basic food vocabulary")
//                .addWord("apple", "jabłko")
//                .addWord("bread", "chleb")
//                .addWord("milk", "mleko")
//                .inCategory("Food & Drinks")
//                .create();
//
//        // Get all sets
//        List<VocabularySetDTO> allSets = LingoLearnApp.VocabularyManager.getAllSets();
//        System.out.println("Total sets: " + allSets.size());
//
//        // Update set description
//        LingoLearnApp.VocabularyManager.updateSet(foodSet, "Food", "Updated food vocabulary description");
//
//        // Create and assign a new category
//        CategoryDTO newCategory = LingoLearnApp.VocabularyManager.createCategory("Advanced Food");
//        LingoLearnApp.VocabularyManager.assignCategory(newCategory, foodSet);
//
//        // 2. Start a flashcard learning session
//        System.out.println("\nStarting flashcard session...");
//        var flashcardSession = LingoLearnApp.LearningSession.flashcards(foodSet);
//
//        // Study loop with flashcards
//        WordDTO currentWord;
//        while ((currentWord = flashcardSession.getCurrentWord()) != null) {
//            System.out.println("Word to learn: " + currentWord.original());
//            // Simulate user input
//            StudyProgressDTO progress = flashcardSession.submitAnswer(currentWord.translation());
//            System.out.println("Progress: " + progress.correctAnswers() + "/" + progress.totalAnswered());
//        }
//
//        // Complete the session
//        var flashcardResult = flashcardSession.complete();
//        System.out.println("Flashcard session completed with accuracy: " + flashcardResult.accuracy() + "%");
//
//        // 3. Take a daily challenge
//        System.out.println("\nStarting daily challenge...");
//        var challengeSession = LingoLearnApp.DailyChallenges.startDaily();
//        var challengeProgress = LingoLearnApp.DailyChallenges.getDailyProgress();
//        System.out.println("Current streak: " + LingoLearnApp.DailyChallenges.getCurrentStreak() + " days");
//
//        // 4. Review problematic words
//        System.out.println("\nStarting review of problematic words...");
//        var problemWords = LingoLearnApp.ProblemWordsReview.getProblemWords();
//        if (!problemWords.isEmpty()) {
//            var reviewSession = LingoLearnApp.ProblemWordsReview.startReview();
//            var reviewProgress = LingoLearnApp.ProblemWordsReview.getReviewProgress();
//            System.out.println("Words needing review: " + problemWords.size());
//        }
//
//        // 5. Take a knowledge test
//        System.out.println("\nStarting knowledge test...");
//        var testSession = LingoLearnApp.KnowledgeTest.startTest(foodSet);
//        var testHistory = LingoLearnApp.KnowledgeTest.getHistory();
//
////        // 6. Generate statistics and reports
////        StudyStatisticsDTO stats = LingoLearnApp.Progress.getStatistics();
////        System.out.println("\nLearning Statistics:");
////        System.out.println("Total words learned: " + stats.totalWordsLearned());
////        System.out.println("Overall accuracy: " + stats.getAccuracy() + "%");
////        System.out.println("Study time today: " + stats.studyTimeToday());
////        System.out.println("Current streak: " + stats.dailyStreak() + " days");
////
////        // Generate reports
////        var dailyReport = LingoLearnApp.Progress.generateDailyReport();
////        var weeklyReport = LingoLearnApp.Progress.generateWeeklyReport();
////
////        // Export data
////        LingoLearnApp.DataManagement.exportData(java.nio.file.Path.of("backup.zip"));
//
//        // Clean up - delete the set if needed
//        LingoLearnApp.VocabularyManager.deleteSet(foodSet);
    }
}
