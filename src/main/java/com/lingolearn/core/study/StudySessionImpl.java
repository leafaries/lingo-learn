package com.lingolearn.core.study;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.StudyResultDTO;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.study.StudyProgressDTO;
import com.lingolearn.entities.AnswerEntity;
import com.lingolearn.entities.SessionEntity;
import com.lingolearn.enums.SessionState;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;
import com.lingolearn.repos.AnswerRepository;
import com.lingolearn.repos.SessionRepository;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class StudySessionImpl implements LingoLearn.StudyManager.Session {
    private final SessionEntity session;
    private final SessionRepository sessionRepo;
    private final AnswerRepository answerRepo;
    private final Instant sessionStart;
    private Iterator<WordDTO> wordIterator;
    private WordDTO currentWord;
    private Instant lastAnswerTime;

    public StudySessionImpl(VocabularySetDTO set, SessionType type, StudyMode mode) {
        this.sessionStart = Instant.now();
        this.lastAnswerTime = sessionStart;
        this.sessionRepo = new SessionRepository();
        this.answerRepo = new AnswerRepository();

        // Initialize session entity
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setMode(mode);
        sessionEntity.setType(type);
        sessionEntity.setState(SessionState.IN_PROGRESS);
        this.session = sessionRepo.save(sessionEntity);

        // Prepare words for study
        List<WordDTO> words = new ArrayList<>(set.items().stream()
                .filter(item -> item instanceof WordDTO)
                .map(item -> (WordDTO) item)
                .toList());
        Collections.shuffle(words);
        this.wordIterator = words.iterator();
        this.nextWord();
    }

    @Override
    public WordDTO getCurrentWord() {
        return currentWord;
    }

    @Override
    public StudyProgressDTO submitAnswer(String answer) {
        if (currentWord == null) {
            throw new IllegalStateException("No current word");
        }

        Instant now = Instant.now();
        Duration responseTime = Duration.between(lastAnswerTime, now);
        boolean isCorrect = answer.trim().equalsIgnoreCase(currentWord.translation().trim());

        // Save answer
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setSession(session);
        answerEntity.setUserInput(answer);
        answerEntity.setCorrect(isCorrect);
        answerEntity.setResponseTime(responseTime);
        answerRepo.save(answerEntity);

        lastAnswerTime = now;
        WordDTO previousWord = currentWord;
        nextWord();

        return calculateProgress(isCorrect);
    }

    @Override
    public StudyResultDTO complete() {
        session.setState(SessionState.COMPLETED);
        session.setEndTime(LocalDateTime.now());
        sessionRepo.save(session);

        return new StudyResultDTO(
                mapToVocabularySetDTO(session.getVocabularySet()),
                session.getMode(),
                session.getType(),
                session.getAnswers().size(),
                (int) session.getAnswers().stream().filter(AnswerEntity::isCorrect).count(),
                calculateAccuracy(),
                Duration.between(sessionStart, Instant.now()),
                calculateAverageResponseTime(),
                getProblematicWords(),
                sessionStart,
                Instant.now()
        );
    }

    @Override
    public void abandon() {
        session.setState(SessionState.ABANDONED);
        session.setEndTime(LocalDateTime.now());
        sessionRepo.save(session);
    }

    private void nextWord() {
        currentWord = wordIterator.hasNext() ? wordIterator.next() : null;
    }

    private StudyProgressDTO calculateProgress(boolean isCorrect) {
        List<AnswerEntity> answers = answerRepo.findBySession(session);
        int total = answers.size();
        int correct = (int) answers.stream().filter(AnswerEntity::isCorrect).count();

        return new StudyProgressDTO(
                currentWord,
                isCorrect,
                total,
                correct,
                total > 0 ? (double) correct / total : 0.0,
                calculateAverageResponseTime()
        );
    }

    private Duration calculateAverageResponseTime() {
        List<AnswerEntity> answers = answerRepo.findBySession(session);
        if (answers.isEmpty()) return Duration.ZERO;

        Duration total = answers.stream()
                .map(AnswerEntity::getResponseTime)
                .reduce(Duration.ZERO, Duration::plus);

        return total.dividedBy(answers.size());
    }

    private double calculateAccuracy() {
        List<AnswerEntity> answers = answerRepo.findBySession(session);
        if (answers.isEmpty()) return 0.0;

        return (double) answers.stream()
                .filter(AnswerEntity::isCorrect)
                .count() / answers.size();
    }

    private List<WordDTO> getProblematicWords() {
        return answerRepo.findBySession(session).stream()
                .filter(answer -> !answer.isCorrect())
                .map(AnswerEntity::getWord)
                .distinct()
                .map(this::mapToWordDTO)
                .collect(Collectors.toList());
    }
}
