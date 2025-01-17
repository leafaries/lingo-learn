package horizontallayers.interfaceadapters.dtos;

import horizontallayers.domain.enums.Difficulty;
import horizontallayers.interfaceadapters.dtos.core.LearningItemDTO;

import java.time.Instant;

/*
 - Words are referenced in answers and statistics
 - Used for CRUD operations
 - Words can be part of multiple sets
 */
public record WordDTO(
        String original,
        String translation,
        Difficulty difficulty,
        String feedback,
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
