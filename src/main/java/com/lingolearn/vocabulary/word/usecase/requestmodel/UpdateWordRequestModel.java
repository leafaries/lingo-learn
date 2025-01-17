package com.lingolearn.vocabulary.word.usecase.requestmodel;

import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.util.List;

public record UpdateWordRequestModel(
        Long id,
        String original,
        String translation,
        String partOfSpeech,
        List<String> exampleSentences,
        Difficulty difficulty
) {
}
