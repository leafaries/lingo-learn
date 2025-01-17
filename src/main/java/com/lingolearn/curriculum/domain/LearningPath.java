package com.lingolearn.curriculum.domain;

public record LearningPath(
        Long id,
        String name,
        List<LearningUnit> units,
        Difficulty targetLevel,
        Language sourceLang,
        Language targetLang
) {
}
