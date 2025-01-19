package com.lingolearn.vocabulary.set.usecase;

import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.util.List;
import java.util.Map;

public record MergeSetResponseModel(
        Long newSetId,
        int totalWords,
        List<String> conflicts,
        Map<Difficulty, Integer> difficultyDistribution
) {
}
