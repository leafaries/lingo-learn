package com.lingolearn.curriculum.domain;

import com.lingolearn.common.domain.Language;
import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.util.List;

public record LearningPath(
        Long id,
        String name,
        List<LearningUnit> units,
        Difficulty targetLevel,
        Language sourceLang,
        Language targetLang
) {
}
