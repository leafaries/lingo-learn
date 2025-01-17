package com.lingolearn.study.manual.domain;

import com.lingolearn.vocabulary.word.domain.Word;

public record ManualInputQuestion(
        Word word,
        String userInput,
        double similarityScore  // For fuzzy matching
) {
}
