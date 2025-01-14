package com.lingolearn.dtos;

import com.lingolearn.models.Category;

import java.time.Instant;
import java.util.List;

public record VocabularySetDTO(
        String name,
        String description,
        Category category,
        List<LearningItemDTO> items,
        Instant createdAt,
        Instant lastModifiedAt
) implements LearningItemDTO {
    @Override
    public String getName() {
        return name;
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
