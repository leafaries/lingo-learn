package com.lingolearn.persistence.import_.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ImportJob(
        UUID id,
        ImportSource source,
        ImportStatus status,
        List<ImportError> errors,
        LocalDateTime startedAt,
        LocalDateTime completedAt
) {
}
