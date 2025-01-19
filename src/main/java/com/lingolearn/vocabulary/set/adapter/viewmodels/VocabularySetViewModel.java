package com.lingolearn.vocabulary.set.adapter.viewmodels;

import java.util.List;

public record VocabularySetViewModel(
        Long id,
        String name,
        String description,
        List<WordViewModel> words
) {
}
