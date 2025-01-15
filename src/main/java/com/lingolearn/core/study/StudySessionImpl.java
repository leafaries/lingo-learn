package com.lingolearn.core.study;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.core.mapping.LingoLearnMapper;
import com.lingolearn.dtos.StudyResultDTO;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.core.LearningItemDTO;
import com.lingolearn.dtos.study.StudyProgressDTO;
import com.lingolearn.entities.AnswerEntity;
import com.lingolearn.entities.SessionEntity;
import com.lingolearn.entities.VocabularySetEntity;
import com.lingolearn.enums.SessionState;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;
import com.lingolearn.exception.StudySessionException;
import com.lingolearn.repos.AnswerRepository;
import com.lingolearn.repos.SessionRepository;
import com.lingolearn.repos.VocabularySetRepository;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class StudySessionImpl implements LingoLearn.StudyManager.Session {
    private final SessionEntity session;
    private final SessionRepository sessionRepo;
    private final AnswerRepository answerRepo;
    private final LingoLearnMapper mapper;

    private final Instant sessionStart;
    private Iterator<WordDTO> wordIterator;
    private WordDTO currentWord;
    private Instant lastAnswerTime;

    public StudySessionImpl(
            String vocabularySetName,
            SessionType type,
            StudyMode mode,
            SessionRepository sessionRepo,
            AnswerRepository answerRepo,
            VocabularySetRepository vocabularySetRepo,
            LingoLearnMapper mapper
    ) {
        Objects.requireNonNull(vocabularySetName, "Vocabulary set name cannot be null");
        Objects.requireNonNull(type, "Session type cannot be null");
        Objects.requireNonNull(mode, "Study mode cannot be null");
        Objects.requireNonNull(sessionRepo, "Session repository cannot be null");
        Objects.requireNonNull(answerRepo, "Answer repository cannot be null");
        Objects.requireNonNull(vocabularySetRepo, "Vocabulary set repository cannot be null");
        Objects.requireNonNull(mapper, "Mapper cannot be null");

        this.sessionRepo = sessionRepo;
        this.answerRepo = answerRepo;
        this.mapper = mapper;

        this.sessionStart = Instant.now();
        this.lastAnswerTime = sessionStart;

        try {
            VocabularySetEntity setEntity = vocabularySetRepo.findByName(vocabularySetName)
                    .orElseThrow(() -> new IllegalArgumentException("Vocabulary set not found: " + vocabularySetName));

            this.session = initializeSession(setEntity, type, mode);
            initializeWords(mapper.toDTO(setEntity));
        } catch (Exception e) {
            throw new StudySessionException("Failed to initialize study session", e);
        }
    }

    @Override
    public WordDTO getCurrentWord() {
        validateSessionActive();
        return currentWord;
    }

    @Override
    public StudyProgressDTO submitAnswer(String answer) {
        validateAnswerSubmission(answer);

        try {
            AnswerEntity answerEntity = createAnswer(answer);
            saveAnswer(answerEntity);
            advanceToNextWord();
            return calculateProgress(answerEntity.isCorrect());
        } catch (Exception e) {
            throw new StudySessionException("Failed to submit answer", e);
        }
    }

    @Override
    public StudyResultDTO complete() {
        validateSessionActive();

        try {
            sessionRepo.beginTransaction();
            session.complete();
            sessionRepo.save(session);
            sessionRepo.commitTransaction();

            return createStudyResult();
        } catch (Exception e) {
            sessionRepo.rollbackTransaction();
            throw new StudySessionException("Failed to complete session", e);
        }
    }

    @Override
    public void abandon() {
        if (session.getState() == SessionState.ABANDONED) {
            return;
        }

        try {
            sessionRepo.beginTransaction();
            session.abandon();
            sessionRepo.save(session);
            sessionRepo.commitTransaction();
        } catch (Exception e) {
            sessionRepo.rollbackTransaction();
            throw new StudySessionException("Failed to abandon session", e);
        }
    }

    private SessionEntity initializeSession(VocabularySetEntity setEntity, SessionType type, StudyMode mode) {
        SessionEntity sessionEntity = new SessionEntity(setEntity, mode, type);
        return sessionRepo.save(sessionEntity);
    }

    private void initializeWords(VocabularySetDTO set) {
        List<WordDTO> words = new ArrayList<>();
        collectWords(set, words);
        Collections.shuffle(words);
        this.wordIterator = words.iterator();
        advanceToNextWord();
    }

    private void collectWords(VocabularySetDTO set, List<WordDTO> accumulator) {
        for (LearningItemDTO item : set.items()) {
            if (item instanceof WordDTO word) {
                accumulator.add(word);
            } else if (item instanceof VocabularySetDTO nestedSet) {
                collectWords(nestedSet, accumulator);
            }
        }
    }

    private void validateSessionActive() {
        if (session.getState() != SessionState.IN_PROGRESS) {
            throw new IllegalStateException("Session is not active");
        }
    }

    private void validateAnswerSubmission(String answer) {
        validateSessionActive();
        Objects.requireNonNull(answer, "Answer cannot be null");
        if (currentWord == null) {
            throw new IllegalStateException("No current word available");
        }
    }

    private AnswerEntity createAnswer(String answer) {
        Instant now = Instant.now();
        Duration responseTime = Duration.between(lastAnswerTime, now);
        boolean isCorrect = validateAnswer(answer);

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setSession(session);
        answerEntity.setWord(mapper.toEntity(currentWord));
        answerEntity.setUserInput(answer.trim());
        answerEntity.setCorrect(isCorrect);
        answerEntity.setResponseTime(responseTime);
        answerEntity.setTimestamp(LocalDateTime.now());

        return answerEntity;
    }

    private boolean validateAnswer(String answer) {
        return answer.trim().equalsIgnoreCase(currentWord.translation().trim());
    }

    private void saveAnswer(AnswerEntity answer) {
        answerRepo.save(answer);
        lastAnswerTime = Instant.now();
    }

    private void advanceToNextWord() {
        currentWord = wordIterator.hasNext() ? wordIterator.next() : null;
    }

    private StudyProgressDTO calculateProgress(boolean isCorrect) {
        List<AnswerEntity> answers = answerRepo.findBySession(session);
        int total = answers.size();
        int correct = (int) answers.stream()
                .filter(AnswerEntity::isCorrect)
                .count();

        return new StudyProgressDTO(
                currentWord,
                isCorrect,
                total,
                correct,
                calculateAccuracy(correct, total),
                calculateAverageResponseTime(answers)
        );
    }

    private StudyResultDTO createStudyResult() {
        List<AnswerEntity> answers = answerRepo.findBySession(session);

        return new StudyResultDTO(
                mapper.toDTO(session.getVocabularySet()),
                session.getMode(),
                session.getType(),
                answers.size(),
                (int) answers.stream().filter(AnswerEntity::isCorrect).count(),
                calculateAccuracy(),
                Duration.between(sessionStart, Instant.now()),
                calculateAverageResponseTime(answers),
                getProblematicWords(answers),
                sessionStart,
                Instant.now()
        );
    }

    private double calculateAccuracy() {
        List<AnswerEntity> answers = answerRepo.findBySession(session);
        return calculateAccuracy(
                (int) answers.stream().filter(AnswerEntity::isCorrect).count(),
                answers.size()
        );
    }

    private double calculateAccuracy(int correct, int total) {
        return total > 0 ? (double) correct / total : 0.0;
    }

    private Duration calculateAverageResponseTime(List<AnswerEntity> answers) {
        if (answers.isEmpty()) {
            return Duration.ZERO;
        }

        Duration total = answers.stream()
                .map(AnswerEntity::getResponseTime)
                .reduce(Duration.ZERO, Duration::plus);

        return total.dividedBy(answers.size());
    }

    private List<WordDTO> getProblematicWords(List<AnswerEntity> answers) {
        return answers.stream()
                .filter(answer -> !answer.isCorrect())
                .map(AnswerEntity::getWord)
                .distinct()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public SessionEntity getSessionEntity() {
        return session;
    }
}
