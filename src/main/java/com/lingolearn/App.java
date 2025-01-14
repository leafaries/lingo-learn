package com.lingolearn;

import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;
import com.lingolearn.models.Word;

import java.util.UUID;

public class App {
    public static void main(String[] args) {
        LingoLearnFacade facade = new LingoLearnFacadeImpl();

        // Setup vocabulary
        System.out.println("Creating vocabulary set...");
        UUID spanishBasics = facade.createVocabularySet("Spanish Basics", "Basic Spanish words");
        UUID helloId = facade.createWord("hello", "hola");
        UUID goodbyeId = facade.createWord("goodbye", "adiós");
        facade.addWordToSet(spanishBasics, helloId);
        facade.addWordToSet(spanishBasics, goodbyeId);

        // Start session
        System.out.println("\nStarting learning session...");
        UUID sessionId = facade.startSession(SessionType.MANUAL_TRANSLATION, StudyMode.REGULAR, spanishBasics);

        // Learning loop
        try {
            while (true) {
                Word word = facade.getNextWord(sessionId);
                System.out.println("\nTranslate: " + word.getOriginal());
                System.out.println("Correct answer: " + word.getTranslation());
                facade.submitAnswer(sessionId, word.getTranslation());

                StudyProgress progress = facade.getProgress(sessionId);
                System.out.printf("Progress: %d/%d words (%.1f%% accuracy)%n",
                        progress.getWordsCompleted(),
                        progress.getTotalWords(),
                        progress.getAccuracyRate() * 100);
            }
        } catch (IllegalStateException e) {
            System.out.println("\nNo more words to learn!");
        }

        // Show results
        facade.endSession(sessionId);
        SessionResult result = facade.getCurrentSessionResult(sessionId);
        System.out.printf("\nSession completed! Score: %d/%d correct%n",
                result.getCorrectAnswers(),
                result.getTotalWords());
    }
}
