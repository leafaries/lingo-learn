package com.lingolearn.dtos;

import java.time.Instant;

public record CategoryDTO(
        String name,
        Instant createdAt,
        Instant lastModifiedAt
) {
}
