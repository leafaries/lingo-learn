package com.lingolearn;

public class StudyProgress {
    private final int wordsCompleted;
    private final int totalWords;
    private final float accuracyRate;

    public StudyProgress(int wordsCompleted, int totalWords, float accuracyRate) {
        if (wordsCompleted < 0 || totalWords < 0 || accuracyRate < 0 || accuracyRate > 1) {
            throw new IllegalArgumentException("Invalid progress values");
        }
        if (wordsCompleted > totalWords) {
            throw new IllegalArgumentException("Completed words cannot exceed total words");
        }
        this.wordsCompleted = wordsCompleted;
        this.totalWords = totalWords;
        this.accuracyRate = accuracyRate;
    }

    public int getWordsCompleted() {
        return wordsCompleted;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public float getAccuracyRate() {
        return accuracyRate;
    }
}
