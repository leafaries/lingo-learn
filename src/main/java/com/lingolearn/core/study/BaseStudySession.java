package com.lingolearn.core.study;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.StudyResultDTO;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.study.StudyProgressDTO;
import com.lingolearn.entities.AnswerEntity;
import com.lingolearn.entities.SessionEntity;
import com.lingolearn.entities.VocabularySetEntity;
import com.lingolearn.entities.WordEntity;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;
import com.lingolearn.repos.AnswerRepository;
import com.lingolearn.repos.SessionRepository;
import com.lingolearn.repos.VocabularySetRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class BaseStudySession implements LingoLearn.StudyManager.Session {
    protected final SessionEntity session;
    protected final SessionRepository sessionRepository;
    protected final AnswerRepository answerRepository;
    protected int currentWordIndex;

    protected BaseStudySession(VocabularySetDTO set, StudyMode mode, SessionType type) {
        this.sessionRepository = new SessionRepository();
        this.answerRepository = new AnswerRepository();

        VocabularySetRepository setRepo = new VocabularySetRepository();
        VocabularySetEntity setEntity = setRepo.findById(set.id())
                .orElseThrow(() -> new IllegalArgumentException("Set not found"));

        this.words = new ArrayList<>(setEntity.getWords());
        this.currentWordIndex = 0;

        // Shuffle words for randomized learning
        java.util.Collections.shuffle(this.words, new Random());

        this.session = new SessionEntity(setEntity, mode, type);
        sessionRepository.save(this.session);
    }

    @Override
    public WordDTO getCurrentWord() {
        if (currentWordIndex >= words.size()) {
            return null;
        }
        return mapWordToDTO(words.get(currentWordIndex));
    }

    @Override
    public StudyProgressDTO submitAnswer(String answer) {
        WordEntity currentWord = words.get(currentWordIndex);
        boolean isCorrect = evaluateAnswer(currentWord, answer);

        // Record the answer
        AnswerEntity answerEntity = new AnswerEntity(
                session,
                currentWord,
                answer,
                isCorrect,
                Duration.ofSeconds(10) // TODO: Implement actual response time tracking
        );
        answerRepository.save(answerEntity);

        // Move to next word
        currentWordIndex++;

        return new StudyProgressDTO(
                isCorrect,
                currentWordIndex,
                words.size(),
                currentWordIndex >= words.size()
        );
    }

    protected abstract boolean evaluateAnswer(WordEntity word, String answer);

    @Override
    public StudyResultDTO complete() {
        session.complete();
        sessionRepository.save(session);

        List<AnswerEntity> answers = answerRepository.findBySession(session);
        return new StudyResultDTO(
                answers.size(),
                (int) answers.stream().filter(AnswerEntity::isCorrect).count(),
                Duration.between(session.getStartTime(), session.getEndTime())
        );
    }

    @Override
    public void abandon() {
        session.abandon();
        sessionRepository.save(session);
    }

    private WordDTO mapWordToDTO(WordEntity entity) {
        return new WordDTO(
                entity.getOriginal(),
                entity.getTranslation(),
                entity.getDifficulty(),
                entity.getCreatedAt().toInstant(),
                entity.getLastModifiedAt().toInstant()
        );
    }
}
