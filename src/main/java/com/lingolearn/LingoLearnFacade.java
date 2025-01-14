package com.lingolearn;

import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;
import com.lingolearn.models.Word;

import java.util.UUID;

/** Main facade interface that clients will interact with */
public interface LingoLearnFacade {
    // Sessions Operations
    UUID startSession(SessionType type, StudyMode mode, VocabularySet vocabularySet);
    void endSession(UUID sessionId);
    SessionResult getCurrentSessionResult(UUID sessionId);
    void submitAnswer(UUID sessionId, String answer);
    Word getNextWord(UUID sessionId);
    StudyProgress getProgress(UUID sessionId);

    // Vocabulary Operations
    UUID createVocabularySet(String name, String description);
    void addWordToSet(UUID setId, UUID wordId);
    void removeWordFromSet(UUID setId, UUID wordId);
    void categorizeSet(UUID setId, UUID categoryId);
    VocabularySet getVocabularySet(UUID setId);
    List<VocabularySet> getAllVocabularySets();

    UUID createWord(String original, String translation);
    void updateWord(UUID categoryId, String name);
    void deleteCategory(UUID categoryId);

    // Learning Operations
    /* void submitAnswer(String answer);
    Word getCurrentWord();
    StudyProgress getProgress();
    List<Word> getProblemWords(); */

    // Challenge and test management
    /* void startDailyChallenge();
    void startKnowledgeTest(VocabularySet set);
    TestResult getTestResult(); */

    // Statistics and reporting
    /* Statistics getUserStatistics();
    Report generateReport(ReportType type, LocalDate from, LocalDate to);
    void resetStatistics(); */

    // Settings management
    /* void updateSettings(Settings settings);
    Settings getSettings();
    void exportSettings(String filePath);
    void importSettings(String filePath); */

    // Data management
    /* void exportData(String filePath, ExportType type);
    void importData(String filePath); */
}
