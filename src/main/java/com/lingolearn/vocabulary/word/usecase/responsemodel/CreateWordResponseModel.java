package com.lingolearn.vocabulary.word.usecase.responsemodel;

import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.util.List;

public record CreateWordResponseModel(
        Long id,
        String original,
        String translation,
        String partOfSpeech,
        List<String> exampleSentences,
        Difficulty difficulty
) {
}
