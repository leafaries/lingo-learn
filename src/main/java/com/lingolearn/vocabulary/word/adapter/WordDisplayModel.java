package com.lingolearn.vocabulary.word.adapter;

import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.util.List;

public record WordDisplayModel(
        Long id,
        String original,
        String translation,
        String partOfSpeech,
        List<String> exampleSentences,
        Difficulty difficulty
) {
}
