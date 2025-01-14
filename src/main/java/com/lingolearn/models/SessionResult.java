package com.lingolearn.models;

import java.util.UUID;

public class SessionResult {
    private final UUID sessionId;
    private final int totalWords;
    private final int correctAnswers;

    public SessionResult(UUID sessionId, int totalWords, int correctAnswers) {
        this.sessionId = sessionId;
        this.totalWords = totalWords;
        this.correctAnswers = correctAnswers;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}
