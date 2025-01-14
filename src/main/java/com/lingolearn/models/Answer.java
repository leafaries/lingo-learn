package com.lingolearn.models;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class Answer {
    private final String userInput;
    private final boolean correct;
    private final UUID wordId;
    private final Duration responseTime;
    private final Instant timestamp = Instant.now();

    public Answer(String userInput, boolean correct, UUID wordId, Duration responseTime) {
        this.userInput = userInput;
        this.correct = correct;
        this.wordId = wordId;
        this.responseTime = responseTime;
    }

    public String getUserInput() {
        return userInput;
    }

    public boolean isCorrect() {
        return correct;
    }

    public UUID getWordId() {
        return wordId;
    }

    public Duration getResponseTime() {
        return responseTime;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
