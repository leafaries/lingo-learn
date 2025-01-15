package com.lingolearn;

import com.lingolearn.core.LingoLearnApp;
import com.lingolearn.dtos.StudyResultDTO;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.study.StudyProgressDTO;

import java.nio.file.Path;

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
        StudyProgressDTO progress = session.submitAnswer("hola");
        StudyResultDTO result = session.complete();

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
//        ReportDTO report = LingoLearnApp.Progress
//                .generateReport(new ReportConfigDTO());

        // Export data
        LingoLearnApp.DataManagement
                .exportData(Path.of("backup.zip"));
    }
}
