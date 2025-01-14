package com.lingolearn.dtos;

import com.lingolearn.enums.Difficulty;

import java.time.Instant;

public record WordDTO(
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
