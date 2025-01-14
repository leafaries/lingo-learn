package com.lingolearn.models;

import com.lingolearn.enums.Difficulty;

import java.time.Instant;
import java.util.Set;

public class Word implements LearningItem {
    private final Instant createdAt;

    private String original;
    private String translation;
    private Difficulty difficulty;
    private Instant lastModifiedAt;

    public Word(String original, String translation) {
        this.createdAt = Instant.now();
        this.original = original;
        this.translation = translation;
        this.difficulty = Difficulty.MEDIUM;
        this.lastModifiedAt = createdAt;
    }

    @Override
    public String getName() {
        return original;
    }

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    @Override
    public Set<Word> getAllWords() {
        return Set.of(this);
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
        updateLastModified();
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
        updateLastModified();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        updateLastModified();
    }

    private void updateLastModified() {
        this.lastModifiedAt = Instant.now();
    }
}
