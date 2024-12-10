package com.lingolearnhub.api;

import lombok.AllArgsConstructor;

/**
 * Represents user performance statistics during learning challenges.
 * Statistics include metrics like the total number of challenges attempted,
 * successfully completed challenges, and the success rate percantage.
 */
@AllArgsConstructor
public class ChallengeStatistics {

    private final int totalChallenges;
    private final int completedChallenges;
    private final double successRate;

    @Override
    public String toString() {
        return "Total: " + totalChallenges +
                ", Completed: " + completedChallenges +
                ", Success Rate: " + successRate + "%";
    }

}
