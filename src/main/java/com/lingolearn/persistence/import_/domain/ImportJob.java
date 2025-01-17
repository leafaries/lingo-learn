package com.lingolearn.persistence.import_.domain;

import java.time.LocalDateTime;
import java.util.List;

public record ImportJob(
        Long id,
        ImportSource source,
        ImportStatus status,
        List<ImportError> errors,
        LocalDateTime startedAt,
        LocalDateTime completedAt
) {
}
