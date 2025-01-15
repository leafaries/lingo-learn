package com.lingolearn.dtos.core;

import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;

import java.time.Instant;

public sealed interface LearningItemDTO
        permits WordDTO, VocabularySetDTO {
    String getName();
    Instant getCreatedAt();
    Instant getLastModifiedAt();
}
