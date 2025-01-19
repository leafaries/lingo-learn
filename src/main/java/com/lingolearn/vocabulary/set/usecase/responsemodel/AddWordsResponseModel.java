package com.lingolearn.vocabulary.set.usecase.responsemodel;

import com.lingolearn.vocabulary.word.domain.Word;

import java.util.List;

public record AddWordsResponseModel(
        Long id,
        String name,
        String description,
        List<Word> words
) {
}
