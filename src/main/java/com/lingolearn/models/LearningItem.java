package com.lingolearn.models;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public interface LearningItem {
    UUID getId();
    String getName();
    Instant getCreatedAt();
    Instant getLastModifiedAt();
    Set<UUID> getAllWordIds();
}
