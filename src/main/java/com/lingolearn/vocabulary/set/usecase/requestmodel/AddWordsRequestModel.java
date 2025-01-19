package com.lingolearn.vocabulary.set.usecase.requestmodel;

import com.lingolearn.vocabulary.word.domain.Word;

import java.util.List;

public record AddWordsRequestModel(
        Long setId,
        List<Word> words
) {
}
