package com.lingolearnhub.progress;

public class ChallengeStatistics {
    private int totalChallenges;
    private int completedChallenges;
    private double successRate;

    public ChallengeStatistics(int totalChallenges, int completedChallenges, double successRate) {
        this.totalChallenges = totalChallenges;
        this.completedChallenges = completedChallenges;
        this.successRate = successRate;
    }

    @Override
    public String toString() {
        return "Total: " + totalChallenges + ", Completed: " + completedChallenges + ", Success Rate: " + successRate + "%";
    }
}
