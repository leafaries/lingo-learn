package com.lingolearn.core;

import com.lingolearn.dtos.CategoryDTO;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LingoLearnApp {
    private static final LingoLearn lingoLearn = new LingoLearnImpl();

    // ==== Vocabulary Management ====
    public static class VocabularyManager {
        public static VocabularySetDTO createSet(String name, String description) {
            return new SetCreator(name, description).create();
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

        public static class Session {
            private final LingoLearn.StudyManager.Session session;

            private Session(LingoLearn.StudyManager.Session session) {
                this.session = session;
            }

            public WordDTO getCurrentWord() {
                return session.getCurrentWord();
            }

            public StudyProgress submitAnswer(String answer) {
                return session.submitAnswer(answer);
            }

            public StudyResult complete() {
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

        public static StudyProgress getDailyProgress() {
            return lingoLearn.challenges().daily().getProgress();
        }

        public static List<Challenge> getHistory() {
            return lingoLearn.challenges().daily().getHistory();
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
    }

    // ==== Knowledge Tests ====
    public static class KnowledgeTest {
        public static LearningSession.Session startTest(VocabularySetDTO set) {
            return new LearningSession.Session(
                    lingoLearn.challenges().test().start(set));
        }

        public static List<TestResult> getHistory() {
            return lingoLearn.challenges().test().getHistory();
        }
    }

    // ==== Progress Tracking ====
    public static class Progress {
        public static StudyStatistics getStatistics() {
            return lingoLearn.statistics().getStudyStats();
        }

        public static void resetStatistics() {
            lingoLearn.statistics().resetStats();
        }

        public static Report generateReport(ReportConfig config) {
            return lingoLearn.statistics().generateReport(config);
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
}
