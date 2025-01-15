package com.lingolearn.core.mapping;

import com.lingolearn.dtos.CategoryDTO;
import com.lingolearn.dtos.StudyResultDTO;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.challenge.TestResultDTO;
import com.lingolearn.dtos.core.LearningItemDTO;
import com.lingolearn.dtos.statistics.StudyStatisticsDTO;
import com.lingolearn.dtos.study.AnswerDTO;
import com.lingolearn.dtos.study.StudyProgressDTO;
import com.lingolearn.entities.*;

import java.time.Duration;
import java.time.ZoneOffset;
import java.util.List;

public final class EntityDTOMapper {
    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

    // Utility class
    private EntityDTOMapper() {
    }

    public static WordDTO toDTO(WordEntity entity) {
        if (entity == null) return null;
        return new WordDTO(
                entity.getId(),
                entity.getOriginal(),
                entity.getTranslation(),
                entity.getDifficulty(),
                entity.getCreatedAt().toInstant(ZONE_OFFSET),
                entity.getLastModifiedAt().toInstant(ZONE_OFFSET)
        );
    }

    public static CategoryDTO toDTO(CategoryEntity entity) {
        if (entity == null) return null;
        return new CategoryDTO(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt().toInstant(ZONE_OFFSET),
                entity.getLastModifiedAt().toInstant(ZONE_OFFSET)
        );
    }

    public static VocabularySetDTO toDTO(VocabularySetEntity entity) {
        if (entity == null) return null;

        List<LearningItemDTO> items = entity.getWords().stream()
                .map(EntityDTOMapper::toDTO)
                .map(dto -> (LearningItemDTO) dto)
                .toList();

        return new VocabularySetDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                toDTO(entity.getCategory()),
                items,
                entity.getCreatedAt().toInstant(ZONE_OFFSET),
                entity.getLastModifiedAt().toInstant(ZONE_OFFSET)
        );
    }

    public static AnswerDTO toDTO(AnswerEntity entity) {
        if (entity == null) return null;
        return new AnswerDTO(
                toDTO(entity.getWord()),
                entity.getUserInput(),
                entity.isCorrect(),
                entity.getResponseTime(),
                entity.getTimestamp().toInstant(ZONE_OFFSET)
        );
    }

    public static StudyStatisticsDTO toDTO(StatisticsEntity entity) {
        if (entity == null) return null;
        return new StudyStatisticsDTO(
                entity.getTotalWordsLearned(),
                entity.getCorrectAnswers(),
                entity.getTotalAnswers(),
                entity.getTotalAnswers() > 0 ?
                        (double) entity.getCorrectAnswers() / entity.getTotalAnswers() : 0.0,
                entity.getTotalStudyTime(),
                entity.getDailyChallengesCompleted(),
                entity.getDate()
        );
    }

    public static StudyProgressDTO toDTO(SessionEntity session, WordDTO currentWord, boolean isCorrect) {
        if (session == null) return null;

        List<AnswerEntity> answers = session.getAnswers();
        int total = answers.size();
        int correct = (int) answers.stream()
                .filter(AnswerEntity::isCorrect)
                .count();

        Duration avgResponseTime = calculateAverageResponseTime(answers);

        return new StudyProgressDTO(
                currentWord,
                isCorrect,
                total,
                correct,
                total > 0 ? (double) correct / total : 0.0,
                avgResponseTime
        );
    }

    public static StudyResultDTO toDTO(SessionEntity session) {
        if (session == null) return null;

        List<AnswerEntity> answers = session.getAnswers();
        int total = answers.size();
        int correct = (int) answers.stream()
                .filter(AnswerEntity::isCorrect)
                .count();

        List<WordDTO> problematicWords = answers.stream()
                .filter(a -> !a.isCorrect())
                .map(AnswerEntity::getWord)
                .distinct()
                .map(EntityDTOMapper::toDTO)
                .toList();

        return new StudyResultDTO(
                toDTO(session.getVocabularySet()),
                session.getMode(),
                session.getType(),
                total,
                correct,
                total > 0 ? (double) correct / total : 0.0,
                Duration.between(session.getStartTime(), session.getEndTime()),
                calculateAverageResponseTime(answers),
                problematicWords,
                session.getStartTime().toInstant(ZONE_OFFSET),
                session.getEndTime().toInstant(ZONE_OFFSET)
        );
    }

    public static TestResultDTO toTestResultDTO(SessionEntity session) {
        if (session == null) return null;

        List<AnswerDTO> answers = session.getAnswers().stream()
                .map(EntityDTOMapper::toDTO)
                .toList();

        List<WordDTO> incorrectAnswers = session.getAnswers().stream()
                .filter(a -> !a.isCorrect())
                .map(AnswerEntity::getWord)
                .distinct()
                .map(EntityDTOMapper::toDTO)
                .toList();

        double score = session.getAnswers().isEmpty() ? 0.0 :
                (double) session.getAnswers().stream()
                        .filter(AnswerEntity::isCorrect)
                        .count() / session.getAnswers().size();

        return new TestResultDTO(
                toDTO(session.getVocabularySet()),
                score,
                answers,
                incorrectAnswers,
                Duration.between(session.getStartTime(), session.getEndTime()),
                session.getStartTime().toInstant(ZONE_OFFSET),
                session.getEndTime().toInstant(ZONE_OFFSET)
        );
    }

    private static Duration calculateAverageResponseTime(List<AnswerEntity> answers) {
        if (answers.isEmpty()) return Duration.ZERO;

        Duration total = answers.stream()
                .map(AnswerEntity::getResponseTime)
                .reduce(Duration.ZERO, Duration::plus);

        return total.dividedBy(answers.size());
    }
}
