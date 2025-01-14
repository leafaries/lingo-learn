package com.lingolearn.enums;

public enum EntityType {
    VOCABULARY_SET,
    WORD,
    CATEGORY,
    SESSION,
    SETTINGS,
    STATISTICS;

    public String getStoragePath() {
        return this.name().toLowerCase();
    }
}
