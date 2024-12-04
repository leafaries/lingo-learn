package com.lingolearnhub.model;

/**
 * Śledzi postępy użytkownika.
 */
public class UserProgress {
    private int correctAnswers;
    private int totalAnswers;

    public void incrementCorrect() {
        correctAnswers++;
    }

    public void incrementTotal() {
        totalAnswers++;
    }

    public double getProgress() {
        return totalAnswers == 0 ? 0 : (double) correctAnswers / totalAnswers * 100;
    }
}
