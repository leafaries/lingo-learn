package com.lingolearn.core.mapping;

import com.lingolearn.dtos.CategoryDTO;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.challenge.DailyChallengeDTO;
import com.lingolearn.dtos.challenge.TestResultDTO;
import com.lingolearn.dtos.config.PreferencesDTO;
import com.lingolearn.dtos.statistics.StudyStatisticsDTO;
import com.lingolearn.dtos.study.AnswerDTO;
import com.lingolearn.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper
public interface LingoLearnMapper {
    LingoLearnMapper INSTANCE = Mappers.getMapper(LingoLearnMapper.class);

    WordDTO toDTO(WordEntity entity);

    CategoryDTO toDTO(CategoryEntity entity);

    VocabularySetDTO toDTO(VocabularySetEntity entity);

    @Mapping(target = "word", source = "word")
    AnswerDTO toDTO(AnswerEntity entity);

    StudyStatisticsDTO toDTO(StatisticsEntity entity);

    @Mapping(target = "session", source = "session")
    DailyChallengeDTO toDTO(DailyChallengeEntity entity);

    @Mapping(target = "vocabularySet", source = "vocabularySet")
    TestResultDTO toDTO(SessionEntity entity);

    PreferencesDTO toDTO(PreferenceEntity entity);

    void updatePreferenceEntity(PreferencesDTO dto, @MappingTarget PreferenceEntity entity);

    // Conversion methods for temporal types
    default Instant map(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.toInstant(ZoneOffset.UTC) : null;
    }

    default LocalDateTime map(Instant instant) {
        return instant != null ? LocalDateTime.ofInstant(instant, ZoneOffset.UTC) : null;
    }
}
