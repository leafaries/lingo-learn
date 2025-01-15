package com.lingolearn.dtos;

import com.lingolearn.dtos.core.LearningItemDTO;

import java.time.Instant;
import java.util.List;

/*
 - Sets are referenced in study sessions and tests
 - Used for CRUD operations through the facade
 - Other entities reference sets (e.g., categories contains sets)
 */
public record VocabularySetDTO(
        String name,
        String description,
        CategoryDTO category,
        List<LearningItemDTO> items,
        double successRate,
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
