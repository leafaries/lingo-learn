package horizontallayers.domain.enums;

/**
 * Defines different types of reports that can be generated in the application.
 */
public enum ReportType {
    /**
     * Daily report showing statistics and progress for a single day
     */
    DAILY,

    /**
     * Weekly report aggregating statistics for a 7-day period
     */
    WEEKLY,

    /**
     * Monthly report showing progress and statistics for a calendar month
     */
    MONTHLY,

    /**
     * Custom report for a user-defined date range
     */
    CUSTOM,

    /**
     * Report focused on problematic words and areas needing improvement
     */
    PROBLEM_AREAS,

    /**
     * Comprehensive report showing all learning activities and progress
     */
    COMPREHENSIVE,

    /**
     * Report showing progress in specific categories
     */
    CATEGORY_BASED,

    /**
     * Report focused on vocabulary set completion and mastery
     */
    SET_BASED;

    /**
     * Returns whether this report type requires a date range
     */
    public boolean requiresDateRange() {
        return this == CUSTOM || this == COMPREHENSIVE;
    }

    /**
     * Returns whether this report type requires category selection
     */
    public boolean requiresCategory() {
        return this == CATEGORY_BASED;
    }

    /**
     * Returns whether this report type requires vocabulary set selection
     */
    public boolean requiresSet() {
        return this == SET_BASED;
    }
}
