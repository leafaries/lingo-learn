package com.lingolearn.core;

import com.lingolearn.dtos.CategoryDTO;
import com.lingolearn.dtos.StudyResultDTO;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.challenge.ChallengeDTO;
import com.lingolearn.dtos.challenge.TestResultDTO;
import com.lingolearn.dtos.config.PreferenceConfigDTO;
import com.lingolearn.dtos.statistics.ReportConfigDTO;
import com.lingolearn.dtos.statistics.ReportDTO;
import com.lingolearn.dtos.statistics.StudyStatisticsDTO;
import com.lingolearn.dtos.study.StudyProgressDTO;

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
            StudyProgressDTO submitAnswer(String answer);
            StudyResultDTO complete();
            void abandon();
        }

        interface FlashcardMode {
            Session start(VocabularySetDTO set);
            PreferenceConfigDTO getPreferences();
            void updatePreferences(PreferenceConfigDTO config);
        }

        interface TranslationMode {
            Session start(VocabularySetDTO set);
            PreferenceConfigDTO getPreferences();
            void updatePreferences(PreferenceConfigDTO config);
        }

        interface ManualMode {
            Session start(VocabularySetDTO set);
            PreferenceConfigDTO getPreferences();
            void updatePreferences(PreferenceConfigDTO config);
        }
    }

    interface ChallengeManager {
        DailyChallenge daily();
        Review review();
        Test test();

        interface DailyChallenge {
            StudyManager.Session start();
            StudyProgressDTO getProgress();
            List<ChallengeDTO> getHistory();
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
        PreferenceConfigDTO getPreferences();
        void updatePreferences(PreferenceConfigDTO config);
    }
}
