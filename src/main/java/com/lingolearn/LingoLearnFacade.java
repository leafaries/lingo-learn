package com.lingolearn;

import com.lingolearn.models.Category;
import com.lingolearn.models.VocabularySet;
import com.lingolearn.models.Word;

import java.nio.file.Path;
import java.util.List;

/** Main facade interface that clients will interact with */
public interface LingoLearnFacade {

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
            VocabularySet create(String name, String description);
            List<VocabularySet> getAll();
            void update(VocabularySet set, String name, String description);
            void addWords(VocabularySet set, List<Word> words);
            void removeWords(VocabularySet set, List<Word> words);
            void delete(VocabularySet set);
        }

        interface WordManager {
            Word create(String original, String translation);
            List<Word> getAll();
            void update(Word word, String original, String translation);
            void delete(Word word);
        }

        interface CategoryManager {
            Category create(String name);
            List<Category> getAll();
            void update(Category category, String name);
            void assignToSet(Category category, VocabularySet set);
            void removeFromSet(Category category, VocabularySet set);
            void delete(Category category);
        }
    }

    interface StudyManager {
        FlashcardMode flashcards();
        TranslationMode translation();
        ManualMode manual();

        interface Session {
            Word getCurrentWord();
            StudyProgress submitAnswer(String answer);
            StudyResult complete();
            void abandon();
        }

        interface FlashcardMode {
            Session start(VocabularySet set);
            PreferenceConfig getPreferences();
            void updatePreferences(PreferenceConfig config);
        }

        interface TranslationMode {
            Session start(VocabularySet set);
            PreferenceConfig getPreferences();
            void updatePreferences(PreferenceConfig config);
        }

        interface ManualMode {
            Session start(VocabularySet set);
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
            List<Word> getProblematicWords();
            StudyProgress getProgress();
        }

        interface Test {
            Session start(VocabularySet set);
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
        KeyboardShortcuts getShortcuts();
        void updateShortcuts(KeyboardShortcuts shortcuts);
    }
}
