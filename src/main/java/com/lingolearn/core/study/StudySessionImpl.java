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
import com.lingolearn.repo.AnswerRepository;
import com.lingolearn.repo.SessionRepository;
import com.lingolearn.repo.VocabularySetRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class StudySessionImpl implements LingoLearn.StudyManager.Session {
    private final SessionEntity session;
    private final SessionRepository sessionRepository;
    private final AnswerRepository answerRepository;
    private Iterator<WordEntity> wordIterator;
    private WordEntity currentWord;

    public StudySessionImpl(VocabularySetDTO set, SessionType type, StudyMode mode) {
        VocabularySetRepository setRepo = new VocabularySetRepository();
        this.sessionRepository = new SessionRepository();
        this.answerRepository = new AnswerRepository();

        VocabularySetEntity setEntity = setRepo.findById(set.id())
                .orElseThrow(() -> new IllegalArgumentException("Set not found"));

        this.session = new SessionEntity(setEntity, mode, type);
        this.session = sessionRepository.save(this.session);

        List<WordEntity> words = new ArrayList<>(setEntity.getWords());
        Collections.shuffle(words); // Randomize word order
        this.wordIterator = words.iterator();
        this.nextWord();
    }

    private void nextWord() {
        currentWord = wordIterator.hasNext() ? wordIterator.next() : null;
    }

    @Override
    public WordDTO getCurrentWord() {
        if (currentWord == null) {
            return null;
        }
        return new WordDTO(
                currentWord.getOriginal(),
                currentWord.getTranslation(),
                currentWord.getDifficulty(),
                currentWord.getCreatedAt().toInstant(),
                currentWord.getLastModifiedAt().toInstant()
        );
    }

    @Override
    public StudyProgressDTO submitAnswer(String answer) {
        if (currentWord == null) {
            throw new IllegalStateException("No current word");
        }

        boolean isCorrect = answer.trim().equalsIgnoreCase(currentWord.getTranslation().trim());

        AnswerEntity answerEntity = new AnswerEntity(
                session,
                currentWord,
                answer,
                isCorrect,
                Duration.ofSeconds(1) // TODO: Implement actual response time tracking
        );

        answerRepository.save(answerEntity);
        nextWord();

        return new StudyProgressDTO(
                session.getAnswers().size(),
                (int) session.getAnswers().stream().filter(AnswerEntity::isCorrect).count(),
                currentWord == null
        );
    }

    @Override
    public StudyResultDTO complete() {
        session.complete();
        sessionRepository.save(session);

        return new StudyResultDTO(
                session.getAnswers().size(),
                (int) session.getAnswers().stream().filter(AnswerEntity::isCorrect).count(),
                Duration.between(session.getStartTime(), session.getEndTime())
        );
    }

    @Override
    public void abandon() {
        session.abandon();
        sessionRepository.save(session);
    }
}
