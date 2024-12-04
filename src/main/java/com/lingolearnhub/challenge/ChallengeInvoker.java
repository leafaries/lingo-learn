package com.lingolearnhub.challenge;

import java.util.ArrayList;
import java.util.List;

public class ChallengeInvoker {
    private List<ChallengeCommand> challengeHistory = new ArrayList<>();

    public void executeChallenge(ChallengeCommand challange) {
        challange.execute();
        challengeHistory.add(challange);
    }
}

/* Example Usage:
public static void main(String[] args) {
    ChallangeInvoker invoker = new ChallengeInvoker();

    ChallangeCommand quizChallenge = new QuizChallenge();
    ChallangeCommand puzzleChallenge = new PuzzleChallenge();
    ChallangeCommand taskChallenge = new TaskChallenge();

    invoker.executeChallenge(quizChallenge); // Executes a quiz challenge
    invoker.executeChallenge(puzzleChallenge); // Executes a puzzle challenge
    invoker.executeChallenge(taskChallenge); // Executes a task challenge
}
 */
