package com.lingolearn.vocabulary.word.adapter.dtos;

import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.util.List;

public record UpdateWordDTO(
        Long id,
        String original,
        String translation,
        String partOfSpeech,
        List<String> exampleSentences,
        Difficulty difficulty
) {
}
