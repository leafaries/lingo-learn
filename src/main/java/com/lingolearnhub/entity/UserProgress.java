package com.lingolearnhub.entity;

public class UserProgress {
    private int correctAnswers;
    private int totalAnswers;

    public void incrementCorrect() {
        correctAnswers++;
    }

    public void incrementTotal() {
        totalAnswers++;
    }

    public double getProgess() {
        return totalAnswers == 0 ? 0 : (double) correctAnswers / totalAnswers * 100;
    }
}
