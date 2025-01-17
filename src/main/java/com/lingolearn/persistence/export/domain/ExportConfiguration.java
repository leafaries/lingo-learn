package com.lingolearn.persistence.export.domain;

import java.util.List;
import java.util.UUID;

public record ExportConfiguration(
        UUID id,
        List<UUID> vocabularySetIds,
        ExportFormat format,
        boolean includeProgress,
        boolean includeStatistics
) {
}
