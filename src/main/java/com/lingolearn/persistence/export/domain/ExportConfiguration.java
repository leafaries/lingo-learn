package com.lingolearn.persistence.export.domain;

import java.util.List;

public record ExportConfiguration(
        Long id,
        List<Long> vocabularySetIds,
        ExportFormat format,
        boolean includeProgress,
        boolean includeStatistics
) {
}
