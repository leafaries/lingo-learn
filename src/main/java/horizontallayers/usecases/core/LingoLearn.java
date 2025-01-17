package horizontallayers.usecases.core;

import horizontallayers.interfaceadapters.dtos.CategoryDTO;
import horizontallayers.interfaceadapters.dtos.StudyResultDTO;
import horizontallayers.interfaceadapters.dtos.VocabularySetDTO;
import horizontallayers.interfaceadapters.dtos.WordDTO;
import horizontallayers.interfaceadapters.dtos.challenge.DailyChallengeDTO;
import horizontallayers.interfaceadapters.dtos.challenge.TestResultDTO;
import horizontallayers.interfaceadapters.dtos.config.PreferencesDTO;
import horizontallayers.interfaceadapters.dtos.statistics.ReportConfigDTO;
import horizontallayers.interfaceadapters.dtos.statistics.ReportDTO;
import horizontallayers.interfaceadapters.dtos.statistics.StudyStatisticsDTO;
import horizontallayers.interfaceadapters.dtos.study.StudyProgressDTO;

import java.nio.file.Path;
import java.util.List;

/**
 * The main LingoLearn facade is designed for flexibility and completeness.
 * It exposes all possible operations and maintains a clean separation
 * of concerns. However, this makes it verbose for common use cases.
 */
public interface LingoLearn {
    VocabularyManager vocabulary();
    StudyManager study();
    ChallengeManager challenges();
    StatisticsManager statistics();
    DataManager data();
    PreferencesManager preferences();

    interface VocabularyManager {
        SetManager sets();
        WordManager words();
        CategoryManager categories();

        interface SetManager {
            VocabularySetDTO create(String name, String description);
            List<VocabularySetDTO> getAll();
            void update(VocabularySetDTO set, String name, String description);
            void addWords(VocabularySetDTO set, List<WordDTO> words);
            void removeWords(VocabularySetDTO set, List<WordDTO> words);
            void delete(VocabularySetDTO set);
        }

        interface WordManager {
            WordDTO create(String original, String translation);
            List<WordDTO> getAll();
            void update(WordDTO word, String original, String translation);
            void delete(WordDTO word);
        }

        interface CategoryManager {
            CategoryDTO create(String name);
            List<CategoryDTO> getAll();
            void update(CategoryDTO category, String name);
            void assignToSet(CategoryDTO category, VocabularySetDTO set);
            void removeFromSet(CategoryDTO category, VocabularySetDTO set);
            void delete(CategoryDTO category);
        }
    }

    interface StudyManager {
        FlashcardMode flashcards();
        TranslationMode translation();
        ManualMode manual();

        /**
         * Represents an active study session.
         * A session manages the flow of words, tracks progress, and handles
         * user answers.
         */
        public interface Session {
            /**
             * Gets the current word being studied.
             * Returns null if the session is complete.
             *
             * @return the current WordDTO or null if no more words
             */
            WordDTO getCurrentWord();

            /**
             * Submits an answer for the current word and advances to the next
             * word. This method should validate the answer and update session
             * statistics.
             *
             * @param answer the user's answer for the current word
             * @return progress information including correctness and overall
             * statistics
             * @throws IllegalStateException if there is no current word
             * (session is complete)
             */
            StudyProgressDTO submitAnswer(String answer);

            /**
             * Completes the session, calculating final statistics and marks it
             * as finished. This should be called when all words have been
             * processed or when ending early.
             *
             * @return final study results including statistics and problematic
             * words
             */
            StudyResultDTO complete();

            /**
             * Abandons the current session without completing it.
             * This should be called when the user wants to exit without finishing.
             */
            void abandon();
        }

        interface FlashcardMode {
            Session start(VocabularySetDTO set);
        }

        interface TranslationMode {
            Session start(VocabularySetDTO set);
        }

        interface ManualMode {
            Session start(VocabularySetDTO set);
        }

//        PreferencesDTO getPreferences();
//        void updatePreferences(PreferencesDTO config);
    }

    interface ChallengeManager {
        DailyChallenge daily();
        Review review();
        Test test();

        interface DailyChallenge {
            StudyManager.Session start();
            StudyProgressDTO getProgress();
            List<DailyChallengeDTO> getHistory();
        }

        interface Review {
            StudyManager.Session startForProblematicWords();
            List<WordDTO> getProblematicWords();
            StudyProgressDTO getProgress();
        }

        interface Test {
            StudyManager.Session start(VocabularySetDTO set);
            List<TestResultDTO> getHistory();
        }
    }

    interface StatisticsManager {
        StudyStatisticsDTO getStudyStats();
        void resetStats();
        ReportDTO generateReport(ReportConfigDTO config);
    }

    interface DataManager {
        void exportData(Path destination);
        void importData(Path source);
        void exportSettings(Path destination);
        void importSettings(Path source);
    }

    interface PreferencesManager {
        PreferencesDTO getPreferences();
        void updatePreferences(PreferencesDTO config);
    }
}
