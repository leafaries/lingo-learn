package com.lingolearn.models;

import java.time.Instant;
import java.util.Set;

public interface LearningItem {
    String getName();
    Instant getCreatedAt();
    Instant getLastModifiedAt();
    Set<Word> getAllWords();
}
