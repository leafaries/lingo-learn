package com.lingolearn.dtos;

import com.lingolearn.dtos.core.LearningItemDTO;
import com.lingolearn.enums.Difficulty;

import java.time.Instant;

/*
 - Words are referenced in answers and statistics
 - Used for CRUD operations
 - Words can be part of multiple sets
 */
public record WordDTO(
        Long id,
        String original,
        String translation,
        Difficulty difficulty,
        Instant createdAt,
        Instant lastModifiedAt
) implements LearningItemDTO {
    @Override
    public String getName() {
        return original;
    }

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }
}
