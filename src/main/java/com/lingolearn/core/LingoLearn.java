package com.lingolearn.core;

import com.lingolearn.dtos.CategoryDTO;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;

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

        interface Session {
            WordDTO getCurrentWord();
            StudyProgress submitAnswer(String answer);
            StudyResult complete();
            void abandon();
        }

        interface FlashcardMode {
            Session start(VocabularySetDTO set);
            PreferenceConfig getPreferences();
            void updatePreferences(PreferenceConfig config);
        }

        interface TranslationMode {
            Session start(VocabularySetDTO set);
            PreferenceConfig getPreferences();
            void updatePreferences(PreferenceConfig config);
        }

        interface ManualMode {
            Session start(VocabularySetDTO set);
            PreferenceConfig getPreferences();
            void updatePreferences(PreferenceConfig config);
        }
    }

    interface ChallengeManager {
        DailyChallenge daily();
        Review review();
        Test test();

        interface DailyChallenge {
            Session start();
            StudyProgress getProgress();
            List<Challenge> getHistory();
        }

        interface Review {
            Session startForProblematicWords();
            List<WordDTO> getProblematicWords();
            StudyProgress getProgress();
        }

        interface Test {
            Session start(VocabularySetDTO set);
            List<TestResult> getHistory();
        }
    }

    interface StatisticsManager {
        StudyStatistics getStudyStats();
        void resetStats();
        Report generateReport(ReportConfig config);
    }

    interface DataManager {
        void exportData(Path destination);
        void importData(Path source);
        void exportSettings(Path destination);
        void importSettings(Path source);
    }

    interface PreferencesManager {
        PreferenceConfig getPreferences();
        void updatePreferences(PreferenceConfig config);
    }
}
