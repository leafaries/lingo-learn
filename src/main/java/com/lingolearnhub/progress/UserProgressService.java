package com.lingolearnhub.progress;

import java.util.Arrays;
import java.util.List;

public class UserProgressService {
    // Example method to update progress after completing a challenge
    public void updateProgressAfterChallenge(String userId, String challengeId, boolean isCompleted) {
        if (isCompleted) {
            // Update user's progress, assuming you have a user repository or database
            // TODO: Implement persistant storage for user data aka add a database and repository
            System.out.println("Updating progress for user: " + userId + " and challenge: " + challengeId);
            // Persist the change in user progress
        }
    }
    public ChallengeStatistics calculateChallengeStatistics(String userId) {
        // Mocked logic for calculating statistics
        int totalChallenges = 10;  // Placeholder value
        int completedChallenges = 7;  // Placeholder value

        double successRate = (double)completedChallenges / totalChallenges * 100;

        return new ChallengeStatistics(totalChallenges, completedChallenges, successRate);
    }

    public List<String> getDailyChallenges(String userId) {
        // This would be a query from a data source
        return Arrays.asList("Vocabulary Quiz", "Grammar Puzzle", "Conversation Task");
    }
}
