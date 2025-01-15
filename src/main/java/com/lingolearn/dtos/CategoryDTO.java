package com.lingolearn.dtos;

import java.time.Instant;

/*
 - Categories are referenced by sets
 - Used for CRUD operations
 - Required for category management operatioons
 */
public record CategoryDTO(
        Long id,
        String name,
        Instant createdAt,
        Instant lastModifiedAt
) {
}
