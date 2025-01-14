package com.lingolearn;

import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;
import com.lingolearn.models.*;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LingoLearnFacadeImpl implements LingoLearnFacade {
    private final Map<UUID, VocabularySet> vocabularySets = new ConcurrentHashMap<>();
    private final Map<UUID, Word> words = new ConcurrentHashMap<>();
    private final Map<UUID, Session> sessions = new ConcurrentHashMap<>();
    private final Map<UUID, Category> categories = new ConcurrentHashMap<>();

    @Override
    public UUID startSession(SessionType type, StudyMode mode, UUID vocabularySetId) {
        VocabularySet set = vocabularySets.get(vocabularySetId);
        if (set == null) throw new IllegalArgumentException("VocabularySet not found");
        Session session = new Session(set, mode, type);
        sessions.put(session.getId(), session);
        return session.getId();
    }

    @Override
    public void endSession(UUID sessionId) {
        Session session = sessions.get(sessionId);
        if (session == null) throw new IllegalArgumentException("Session not found");
        session.complete();
    }

    @Override
    public SessionResult getCurrentSessionResult(UUID sessionId) {
        Session session = sessions.get(sessionId);
        if (session == null) throw new IllegalArgumentException("Session not found");

        List<Answer> answers = session.getAnswers();
        return new SessionResult(
                session.getId(),
                answers.size(),
                (int) answers.stream().filter(Answer::isCorrect).count()
        );
    }

    @Override
    public void submitAnswer(UUID sessionId, String answer) {
        Session session = sessions.get(sessionId);
        if (session == null) throw new IllegalArgumentException("Session not found");

        Word currentWord = getNextWord(sessionId);
        boolean isCorrect = currentWord.getTranslation().equalsIgnoreCase(answer.trim());
        Answer ans = new Answer(answer, isCorrect, currentWord.getId(), Duration.ofSeconds(1));
        session.addAnswer(ans);
    }

    @Override
    public Word getNextWord(UUID sessionId) {
        Session session = sessions.get(sessionId);
        if (session == null) throw new IllegalArgumentException("Session not found");

        Set<UUID> allWords = session.getLearningItem().getAllWordIds();
        Set<UUID> answeredWords = session.getAnswers().stream()
                .map(Answer::getWordId)
                .collect(Collectors.toSet());

        return allWords.stream()
                .filter(id -> !answeredWords.contains(id))
                .findFirst()
                .map(words::get)
                .orElseThrow(() -> new IllegalStateException("No more words in session"));
    }

    @Override
    public StudyProgress getProgress(UUID sessionId) {
        Session session = sessions.get(sessionId);
        if (session == null) throw new IllegalArgumentException("Session not found");

        int total = session.getLearningItem().getAllWordIds().size();
        int completed = session.getAnswers().size();
        float accuracy = completed > 0
                ? (float) session.getAnswers().stream().filter(Answer::isCorrect).count() / completed
                : 0.0f;

        return new StudyProgress(completed, total, accuracy);
    }

    @Override
    public UUID createVocabularySet(String name, String description) {
        VocabularySet set = new VocabularySet(name, description);
        vocabularySets.put(set.getId(), set);
        return set.getId();
    }

    @Override
    public void addWordToSet(UUID setId, UUID wordId) {
        VocabularySet set = vocabularySets.get(setId);
        Word word = words.get(wordId);
        if (set == null || word == null) throw new IllegalArgumentException("Set or word not found");
        set.addItem(word);
    }

    @Override
    public void removeWordFromSet(UUID setId, UUID wordId) {
        VocabularySet set = vocabularySets.get(setId);
        Word word = words.get(wordId);
        if (set == null || word == null) throw new IllegalArgumentException("Set or word not found");
        set.removeItem(word);
    }

    @Override
    public void categorizeSet(UUID setId, UUID categoryId) {
        VocabularySet set = vocabularySets.get(setId);
        Category category = categories.get(categoryId);
        if (set == null || category == null) throw new IllegalArgumentException("Set or category not found");
        set.setCategoryId(categoryId);
    }

    @Override
    public VocabularySet getVocabularySet(UUID setId) {
        VocabularySet set = vocabularySets.get(setId);
        if (set == null) throw new IllegalArgumentException("Set not found");
        return set;
    }

    @Override
    public List<VocabularySet> getAllVocabularySets() {
        return new ArrayList<>(vocabularySets.values());
    }

    @Override
    public UUID createWord(String original, String translation) {
        Word word = new Word(original, translation);
        words.put(word.getId(), word);
        return word.getId();
    }

    @Override
    public void updateWord(UUID wordId, String name) {
        Word word = words.get(wordId);
        if (word == null) throw new IllegalArgumentException("Word not found");
        word.setOriginal(name);
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        if (categories.remove(categoryId) == null) {
            throw new IllegalArgumentException("Category not found");
        }
        vocabularySets.values().stream()
                .filter(set -> categoryId.equals(set.getCategoryId()))
                .forEach(set -> set.setCategoryId(null));
    }
}
