package com.lingolearn.dtos.statistics;

import java.time.LocalDate;
import java.util.Set;

public record ReportConfigDTO(
        LocalDate fromDate,
        LocalDate toDate,
        Set<ReportMetric> includedMetrics,
        boolean includeProblematicWords,
        boolean includeSetStatistics
) {

}
