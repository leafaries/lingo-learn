package com.lingolearn;

import com.lingolearn.core.LingoLearnApp;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;

public class App {
    public static void main(String[] args) {
        // Create a vocabulary set
        VocabularySetDTO travelSet = LingoLearnApp.VocabularyManager
                .new SetCreator("Travel", "Essential travel vocabulary")
                .addWord("hello", "hola")
                .addWord("goodbye", "adiós")
                .inCategory("Travel")
                .create();

        // Start a flashcard session
        LingoLearnApp.LearningSession.Session session = LingoLearnApp.LearningSession
                .flashcards(travelSet);

        // Study loop
        WordDTO word = session.getCurrentWord();
        StudyProgress progress = session.submitAnswer("hola");
        StudyResult result = session.complete();

        // Start daily challenge
        LingoLearnApp.LearningSession.Session dailyChallenge = LingoLearnApp.DailyChallenges
                .startDaily();

        // Review problem words
        LingoLearnApp.LearningSession.Session review = LingoLearnApp.ProblemWordsReview
                .startReview();

        // Take a test
        LingoLearnApp.LearningSession.Session test = LingoLearnApp.KnowledgeTest
                .startTest(travelSet);

        // Generate report
        Report report = LingoLearnApp.Progress
                .generateReport(new ReportConfig(/* config params */));

        // Export data
        LingoLearnApp.DataManagement
                .exportData(Path.of("backup.zip"));
    }
}
