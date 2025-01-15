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
import com.lingolearn.enums.ReportType;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LingoLearnApp {
    private static final LingoLearn lingoLearn = new LingoLearnImpl();

    // ==== Vocabulary Management ====
    public static class VocabularyManager {
        public static VocabularySetDTO createSet(String name, String description) {
            return new SetCreator(name, description).create();
        }

        public static void updateSet(VocabularySetDTO set, String newName, String newDescription) {
            lingoLearn.vocabulary().sets().update(set, newName, newDescription);
        }

        public static void deleteSet(VocabularySetDTO set) {
            lingoLearn.vocabulary().sets().delete(set);
        }

        public static List<VocabularySetDTO> getAllSets() {
            return lingoLearn.vocabulary().sets().getAll();
        }

        public static CategoryDTO createCategory(String name) {
            return lingoLearn.vocabulary().categories().create(name);
        }

        public static void assignCategory(CategoryDTO category, VocabularySetDTO set) {
            lingoLearn.vocabulary().categories().assignToSet(category, set);
        }

        public static void removeCategory(CategoryDTO category, VocabularySetDTO set) {
            lingoLearn.vocabulary().categories().removeFromSet(category, set);
        }

        public static List<CategoryDTO> getAllCategories() {
            return lingoLearn.vocabulary().categories().getAll();
        }

        public static class SetCreator {
            private final String name;
            private final String description;
            private final List<WordPair> words = new ArrayList<>();
            private String category;

            public SetCreator(String name, String description) {
                this.name = name;
                this.description = description;
            }

            public SetCreator addWord(String original, String translation) {
                words.add(new WordPair(original, translation));
                return this;
            }

            public SetCreator addWords(List<WordPair> wordPairs) {
                words.addAll(wordPairs);
                return this;
            }

            public SetCreator inCategory(String categoryName) {
                this.category = categoryName;
                return this;
            }

            public VocabularySetDTO create() {
                VocabularySetDTO set = lingoLearn.vocabulary().sets()
                        .create(name, description);

                if (!words.isEmpty()) {
                    List<WordDTO> wordDTOs = words.stream()
                            .map(pair -> lingoLearn.vocabulary().words()
                                    .create(pair.original(), pair.translation()))
                            .toList();
                    lingoLearn.vocabulary().sets().addWords(set, wordDTOs);
                }

                if (category != null) {
                    CategoryDTO categoryDTO = lingoLearn.vocabulary().categories()
                            .create(category);
                    lingoLearn.vocabulary().categories()
                            .assignToSet(categoryDTO, set);
                }

                return set;
            }
        }
    }

    // ==== Learning Sessions ====
    public static class LearningSession {
        public static Session flashcards(VocabularySetDTO set) {
            return new Session(lingoLearn.study().flashcards().start(set));
        }

        public static Session multipleChoice(VocabularySetDTO set) {
            return new Session(lingoLearn.study().translation().start(set));
        }

        public static Session manualTranslation(VocabularySetDTO set) {
            return new Session(lingoLearn.study().manual().start(set));
        }

        public static PreferenceConfigDTO getPreferredMode(SessionType type) {
            switch (type) {
                case FLASHCARD -> {
                    return lingoLearn.study().flashcards().getPreferences();
                }
                case TRANSLATION_LOGIC -> {
                    return lingoLearn.study().translation().getPreferences();
                }
                case MANUAL_TRANSLATION -> {
                    return lingoLearn.study().manual().getPreferences();
                }
                default -> throw new IllegalArgumentException("Unknown session type: " + type);
            }
        }

        public static void setPreferredMode(SessionType type, PreferenceConfigDTO config) {
            switch (type) {
                case FLASHCARD -> lingoLearn.study().flashcards().updatePreferences(config);
                case TRANSLATION_LOGIC -> lingoLearn.study().translation().updatePreferences(config);
                case MANUAL_TRANSLATION -> lingoLearn.study().manual().updatePreferences(config);
                default -> throw new IllegalArgumentException("Unknown session type: " + type);
            }
        }

        public static class Session {
            private final LingoLearn.StudyManager.Session session;

            private Session(LingoLearn.StudyManager.Session session) {
                this.session = session;
            }

            public WordDTO getCurrentWord() {
                return session.getCurrentWord();
            }

            public StudyProgressDTO submitAnswer(String answer) {
                return session.submitAnswer(answer);
            }

            public StudyResultDTO complete() {
                return session.complete();
            }

            public void abandon() {
                session.abandon();
            }
        }
    }

    // ==== Daily Challenges ====
    public static class DailyChallenges {
        public static LearningSession.Session startDaily() {
            return new LearningSession.Session(
                    lingoLearn.challenges().daily().start());
        }

        public static StudyProgressDTO getDailyProgress() {
            return lingoLearn.challenges().daily().getProgress();
        }

        public static List<ChallengeDTO> getHistory() {
            return lingoLearn.challenges().daily().getHistory();
        }

        public static int getCurrentStreak() {
            StudyStatisticsDTO stats = lingoLearn.statistics().getStudyStats();
            return stats.getDailyStreak();
        }
    }

    // ==== Problem Words Review ====
    public static class ProblemWordsReview {
        public static LearningSession.Session startReview() {
            return new LearningSession.Session(
                    lingoLearn.challenges().review().startForProblematicWords());
        }

        public static List<WordDTO> getProblemWords() {
            return lingoLearn.challenges().review().getProblematicWords();
        }

        public static StudyProgressDTO getReviewProgress() {
            return lingoLearn.challenges().review().getProgress();
        }
    }

    // ==== Knowledge Tests ====
    public static class KnowledgeTest {
        public static LearningSession.Session startTest(VocabularySetDTO set) {
            return new LearningSession.Session(
                    lingoLearn.challenges().test().start(set));
        }

        public static List<TestResultDTO> getHistory() {
            return lingoLearn.challenges().test().getHistory();
        }
    }

    // ==== Progress Tracking ====
    public static class Progress {
        public static StudyStatisticsDTO getStatistics() {
            return lingoLearn.statistics().getStudyStats();
        }

        public static void resetStatistics() {
            lingoLearn.statistics().resetStats();
        }

        public static ReportDTO generateReport(ReportConfigDTO config) {
            return lingoLearn.statistics().generateReport(config);
        }

        public static ReportDTO generateDailyReport() {
            ReportConfigDTO config = new ReportConfigDTO(
                    ReportType.DAILY,
                    null,
                    null,
                    null
            );
            return generateReport(config);
        }

        public static ReportDTO generateWeeklyReport() {
            ReportConfigDTO config = new ReportConfigDTO(
                    ReportType.WEEKLY,
                    null,
                    null,
                    null
            );
            return generateReport(config);
        }

        public static ReportDTO generateMonthlyReport() {
            ReportConfigDTO config = new ReportConfigDTO(
                    ReportType.MONTHLY,
                    null,
                    null,
                    null
            );
            return generateReport(config);
        }
    }

    // ==== Data Management ====
    public static class DataManagement {
        public static void exportData(Path destination) {
            lingoLearn.data().exportData(destination);
        }

        public static void importData(Path source) {
            lingoLearn.data().importData(source);
        }

        public static void exportSettings(Path destination) {
            lingoLearn.data().exportSettings(destination);
        }

        public static void importSettings(Path source) {
            lingoLearn.data().importSettings(source);
        }
    }

    // ==== Preferences Management ====
    public static class PreferencesManager {
        public static PreferenceConfigDTO getPreferences() {
            return lingoLearn.preferences().getPreferences();
        }

        public static void updatePreferences(PreferenceConfigDTO config) {
            lingoLearn.preferences().updatePreferences(config);
        }

        public static void setKeyboardShortcuts(Map<String, String> shortcuts) {
            PreferenceConfigDTO config = getPreferences();
            config.setKeyboardShortcuts(shortcuts);
            updatePreferences(config);
        }

        public static void setDefaultSessionType(SessionType type) {
            PreferenceConfigDTO config = getPreferences();
            config.setDefaultSessionType(type);
            updatePreferences(config);
        }

        public static void setDefaultStudyMode(StudyMode mode) {
            PreferenceConfigDTO config = getPreferences();
            config.setDefaultStudyMode(mode);
            updatePreferences(config);
        }

        public static void setSoundEnabled(boolean enabled) {
            PreferenceConfigDTO config = getPreferences();
            config.setSoundEnabled(enabled);
            updatePreferences(config);
        }

        public static void setDarkMode(boolean enabled) {
            PreferenceConfigDTO config = getPreferences();
            config.setDarkMode(enabled);
            updatePreferences(config);
        }
    }
}
