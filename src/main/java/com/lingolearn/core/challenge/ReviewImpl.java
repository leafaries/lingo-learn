package com.lingolearn.core.challenge;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.core.study.StudySessionImpl;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.study.AnswerDTO;
import com.lingolearn.dtos.study.StudyProgressDTO;
import com.lingolearn.entities.AnswerEntity;
import com.lingolearn.entities.SessionEntity;
import com.lingolearn.entities.WordEntity;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;
import com.lingolearn.repos.AnswerRepository;
import com.lingolearn.repos.SessionRepository;
import com.lingolearn.repos.WordRepository;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ReviewImpl {
    private final WordRepository wordRepo;
    private final SessionRepository sessionRepo;
    private final AnswerRepository answerRepo;

    public ReviewImpl() {
        this.wordRepo = new WordRepository();
        this.sessionRepo = new SessionRepository();
        this.answerRepo = new AnswerRepository();
    }

    @Override
    public LingoLearn.StudyManager.Session startForProblematicWords() {
        List<WordEntity> problematicWords = wordRepo.findProblematicWords(0.7);
        if (problematicWords.isEmpty()) {
            throw new IllegalStateException("No problematic words to review");
        }

        List<WordDTO> wordDTOs = problematicWords.stream()
                .map(this::mapToWordDTO)
                .collect(Collectors.toList());

        // Create a temporary review set
        VocabularySetDTO reviewSet = new VocabularySetDTO(
                null, // temporary sets don't need persistent IDs
                "Review Set",
                "Automatically generated review set",
                null, // no category for review sets
                new ArrayList<>(wordDTOs),
                Instant.now(),
                Instant.now()
        );

        return new StudySessionImpl(reviewSet, SessionType.MANUAL_TRANSLATION, StudyMode.REVIEW);
    }

    @Override
    public List<WordDTO> getProblematicWords() {
        return wordRepo.findProblematicWords(0.7).stream()
                .map(this::mapToWordDTO)
                .toList();
    }

    @Override
    public StudyProgressDTO getProgress() {
        // Get initial problematic words (those that triggered the review)
        List<WordDTO> initialProblematicWords = wordRepo.findProblematicWords(0.7).stream()
                .map(this::mapToWordDTO)
                .toList();

        // Get current problematic words (those still needing practice)
        List<WordDTO> currentProblematicWords = wordRepo.findProblematicWords(0.5).stream()
                .map(this::mapToWordDTO)
                .toList();

        int totalWords = initialProblematicWords.size();
        int improvedWords = totalWords - currentProblematicWords.size();
        double accuracy = totalWords > 0 ? (double) improvedWords / totalWords : 0.0;

        // Calculate average response time for review sessions
        Duration averageResponseTime = calculateAverageReviewResponseTime();

        return new StudyProgressDTO(
                null, // current word not applicable for overall progress
                false, // isCorrect not applicable for overall progress
                totalWords,
                improvedWords,
                accuracy,
                averageResponseTime
        );
    }

    private Duration calculateAverageReviewResponseTime() {
        // Get all review sessions from the last week
        LocalDateTime weekAgo = LocalDateTime.now().minusWeeks(1);
        List<SessionEntity> reviewSessions = sessionRepo.findByStudyModeAndAfter(
                StudyMode.REVIEW,
                weekAgo
        );

        if (reviewSessions.isEmpty()) {
            return Duration.ZERO;
        }

        // Calculate average response time across all review sessions
        long totalAnswers = 0;
        Duration totalTime = Duration.ZERO;

        for (SessionEntity session : reviewSessions) {
            List<AnswerEntity> answers = answerRepo.findBySession(session);
            totalAnswers += answers.size();
            totalTime = answers.stream()
                    .map(AnswerEntity::getResponseTime)
                    .reduce(totalTime, Duration::plus);
        }

        return totalAnswers > 0 ? totalTime.dividedBy(totalAnswers) : Duration.ZERO;
    }

    private WordDTO mapToWordDTO(WordEntity entity) {
        return new WordDTO(
                entity.getId(),
                entity.getOriginal(),
                entity.getTranslation(),
                entity.getDifficulty(),
                entity.getCreatedAt().toInstant(),
                entity.getLastModifiedAt().toInstant()
        );
    }

    private List<AnswerDTO> mapToAnswerDTOs(List<AnswerEntity> entities) {
        return entities.stream()
                .map(entity -> new AnswerDTO(
                        mapToWordDTO(entity.getWord()),
                        entity.getUserInput(),
                        entity.isCorrect(),
                        entity.getResponseTime(),
                        entity.getTimestamp().toInstant()
                ))
                .collect(Collectors.toList());
    }
}
