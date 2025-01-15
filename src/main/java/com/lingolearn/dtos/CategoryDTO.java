package com.lingolearn.dtos;

import java.time.Instant;
import java.util.Set;

/*
 - Categories are referenced by sets
 - Used for CRUD operations
 - Required for category management operatioons
 */
public record CategoryDTO(
        String name,
        String description,
        Set<VocabularySetDTO> vocabularySets,
        Instant createdAt,
        Instant lastModifiedAt
) {
}
