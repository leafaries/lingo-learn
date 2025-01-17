package com.lingolearn.vocabulary.set.domain.exception;

import java.util.UUID;

public class VocabularySetNotFoundException extends RuntimeException {
    public VocabularySetNotFoundException(UUID id) {
        super("Vocabulary set not found with id: " + id);
    }
}
