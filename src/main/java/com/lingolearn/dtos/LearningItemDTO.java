package com.lingolearn.dtos;

import java.time.Instant;

public sealed interface LearningItemDTO
        permits WordDTO, VocabularySetDTO {
    String getName();
    Instant getCreatedAt();
    Instant getLastModifiedAt();
}
