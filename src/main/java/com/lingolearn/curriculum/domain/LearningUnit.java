package com.lingolearn.curriculum.domain;

import com.lingolearn.vocabulary.set.domain.VocabularySet;

import java.util.List;

public record LearningUnit(
        Long id,
        String topic,
        List<VocabularySet> requiredSets,
        List<VocabularyUnit> prerequisites,
        int ordinalNumber,
        UnitStatus status
) {
}
