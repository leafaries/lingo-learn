package com.lingolearn.vocabulary.set.usecase.requestmodel;

import com.lingolearn.vocabulary.word.domain.Word;

import java.util.List;
import java.util.UUID;

public record AddWordsRequestModel(
        UUID setId,
        List<Word> words
) {
}
