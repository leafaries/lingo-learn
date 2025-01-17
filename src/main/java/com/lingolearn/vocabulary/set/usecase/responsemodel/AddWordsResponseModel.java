package com.lingolearn.vocabulary.set.usecase.responsemodel;

import com.lingolearn.vocabulary.word.domain.Word;

import java.util.List;
import java.util.UUID;

public record AddWordsResponseModel(
        UUID id,
        String name,
        String description,
        List<Word> words
) {
}
