package com.lingolearn.vocabulary.set.adapter.viewmodel;

import java.util.List;
import java.util.UUID;

public record VocabularySetViewModel(
        UUID id,
        String name,
        String description,
        List<WordViewModel> words
) {
}
